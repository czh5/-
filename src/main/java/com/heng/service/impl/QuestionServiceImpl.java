package com.heng.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.heng.core.CoreProcessor;
import com.heng.repository.QuestionRepository;
import com.heng.service.QuestionService;


/**
 * <p>核心问答业务实现类</p>
 *
 * @Author heng
 * @Date 2023/3/19 21:48
 * @version 1.0
 */
@Service
@Primary
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CoreProcessor queryProcess;

    @Override
    public String loadTips() throws Exception {
        String tips = "当前可查询的水产品包括：" + arrayToString(questionRepository.loadTipsWithAquaticProducts());
        return tips;
    }

    @Override
    public String answer(String question) throws Exception {

        /**
         * reStrings的形式是 {模板编号,词性,xxx}，也可能是{模板编号,xxx,词性}
         * 第一个词性作为主体，命名为body，例如 【nap 俗称】中的nap
         * 第二个词性作为客体，命名为obj，例如【养殖于 nve】中的nve
         * */
        List<String> reStrings = queryProcess.analysis(question);

        int modelIndex = Integer.valueOf(reStrings.get(0));
        String answer = null;
        String body = reStrings.get(1);
        String obj = reStrings.get(2);

        /**匹配问题模板*/
        switch (modelIndex) {
            case 0:
                answer = questionRepository.getAquaticProductsCommonName(body); //水产品别名
                break;
            case 1:
                answer = questionRepository.getAquaticProductsDisease(body);    //水产品可能患有疾病
                break;
            case 2:
                String picturePath = questionRepository.getAquaticProductsPicture(body);    //水产品图片
                answer = "<a target=\"_blank\" rel=\"noopener noreferrer\" href=\"" + picturePath +
                            "\"><img src=\"" + picturePath + "\" title=\"点击查看大图\"></a>";
                break;
            case 3:
                answer = questionRepository.getAquaticProductsHabitat(body);    //水产品栖息地
                break;
            case 4:
                answer = questionRepository.getAquaticProductsFeatures(body);   //水产品特征
                break;
            case 5:
                answer = questionRepository.getAquaticProductsBreedingSeason(body); //水产品繁殖季节
                break;
            case 6:
                answer = questionRepository.getAquaticProductsVenue(body);      //水产品育苗场地
                break;
            case 7:
                answer = questionRepository.getAquaticProductsBait(body);       //水产品饲养饵料
                break;
            case 8:
                answer = questionRepository.getBaitNutrients(body);             //饲料营养成分
                break;
            case 9:
                answer = questionRepository.getDiseasePathogen(body);           //疾病病因
                break;
            case 10:
                answer = questionRepository.getDiseaseSymptom(body);            //疾病症状
                break;
            case 11:
                answer = questionRepository.getDiseasePrevention(body);         //疾病防治方法
                break;
            case 12:
                answer = questionRepository.getEquipmentFunction(body);         //设备作用
                break;
            case 13:
                answer = questionRepository.getVenueEquipment(body);            //场地设备
                break;
            case 14:
                answer = questionRepository.getVenueDepth(body);                //场地池深
                break;
            case 15:
                answer = questionRepository.getVenueArea(body);                 //场地面积
                break;
            case 16:
                answer = questionRepository.getAquaticProductsLarvaeGrow(body);         //水产品幼体成长
                break;
            case 17:
                answer = questionRepository.getAquaticProductsLarvaeBait(body);        //水产品幼体饲养饲料
                break;
            case 18:
                answer = arrayToString(questionRepository.getSthToVenue(obj));          //养殖于育苗场地
                break;
            case 19:
                answer = arrayToString(questionRepository.getSthToDisease(obj));          //可能患有疾病
                break;
            case 20:
                answer = arrayToString(questionRepository.getSthToAquaticProducts(obj));    //成长为水产品
                break;
            case 21:
                answer = arrayToString(questionRepository.getSthToAquaticProductsLarvae(obj));  //成长为水产品幼体
                break;
            case 22:
                answer = arrayToString(questionRepository.getSthToBait(obj));   //投喂饵料
                break;
            case 23:
                answer = arrayToString(questionRepository.getSthToHabitat(obj));    //栖息于栖息地
                break;
            case 24:
                answer = arrayToString(questionRepository.getSthToEquipment(obj));  //需要配备设备
                break;
            case 25:
                answer = body + questionRepository.getAquaticProductsInfo(body).toString();    //水产品信息
                break;
            default:
                break;
        }
        System.out.println(answer);
        if (answer != null && !"".equals(answer) && !("\\N").equals(answer)) {
            return answer;
        } else {
            return "抱歉,我没有找到你要的答案";
        }
    }

    /**
     * 将String数组转化为String
     */
    private String arrayToString(String[] strings) {
        StringBuffer s = new StringBuffer();
        for(String str : strings) {
            s.append(str + "、");
        }
        return s.substring(0,s.length()-1);
    }


}
