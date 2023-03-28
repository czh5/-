package com.heng.config;

import com.heng.utils.CustomDictWordUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * <p>全局配启动 -- 初始化项目时，执行命令，将相关额外的自定义词典加载下</p>
 *
 * @Author heng
 * @Date 2023/3/17 20:12
 * @version 1.0
 */
@Component
public class ConfigRunner implements CommandLineRunner {

    //缓存
    @Value("${HanLP.CustomDictionary.cache.path}")
    private String cacheDictPath;

    //水产品字典
    @Value("${rootDirPath}/dictionary/marine_products/aquaticProductsDict.txt")
    private String aquaticProductsDictPath;

    //饵料字典
    @Value("${rootDirPath}/dictionary/marine_products/baitDict.txt")
    private String baitDictPath;

    //疾病字典
    @Value("${rootDirPath}/dictionary/marine_products/diseaseDict.txt")
    private String diseaseDictPath;

    //设备字典
    @Value("${rootDirPath}/dictionary/marine_products/equipmentDict.txt")
    private String equipmentDictPath;

    //栖息地字典
    @Value("${rootDirPath}/dictionary/marine_products/habitatDict.txt")
    private String habitatDictPath;

    //场地字典
    @Value("${rootDirPath}/dictionary/marine_products/venueDict.txt")
    private String venueDictPath;

    //水产品幼体字典
    @Value("${rootDirPath}/dictionary/marine_products/aquaticProductsLarvaeDict.txt")
    private String aquaticProductsLarvaeDictPath;

    @Override
    public void run(String... args){
        //先删除缓存
        File file = new File(cacheDictPath);
        if(file.exists()){
            file.delete();
            System.out.println("CustomDictionary.txt.bin delete success .");
        }

        /**加载自定义的【水产品】字典 == 设置词性 nap 0*/
        loadDict(aquaticProductsDictPath,0);
        /**加载自定义的【饵料】字典 == 设置词性 nba 0*/
        loadDict(baitDictPath,1);
        /**加载自定义的【疾病】字典 == 设置词性 ndi 0*/
        loadDict(diseaseDictPath,2);
        /**加载自定义的【设备】字典 == 设置词性 neq 0*/
        loadDict(equipmentDictPath,3);
        /**加载自定义的【栖息地】字典 == 设置词性 nha 0*/
        loadDict(habitatDictPath,4);
        /**加载自定义的【场地】字典 == 设置词性 nve 0*/
        loadDict(venueDictPath,5);
        /**加载自定义的【水产品幼体】字典 == 设置词性 napl 0*/
        loadDict(aquaticProductsLarvaeDictPath,6);

    }

    /**
     * 加载自定义词性字典
     * @param path 字典路径
     * @param type 类型
     */
    public void loadDict(String path,Integer type) {
        File file = new File(path);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            addCustomDictionary(br, type);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }


    /**
     * 添加自定义分词及其词性，注意数字0表示频率，不能没有
     *
     * @param br 字节流（读）
     * @param type 字典类型
     */
    public void addCustomDictionary(BufferedReader br, int type) {

        String word;
        try {
            while ((word = br.readLine()) != null) {
                switch (type) {
                    /**设置水产品名词词性 == nap 0*/
                    case 0:
                        CustomDictWordUtils.setNatureAndFrequency(word,"nap 0",true);
                        break;
                    /**设置饵料名词 词性 == nba 0*/
                    case 1:
                        CustomDictWordUtils.setNatureAndFrequency(word,"nba 0",true);
                        break;
                    /**设置疾病名词 词性 == ndi 0*/
                    case 2:
                        CustomDictWordUtils.setNatureAndFrequency(word,"ndi 0",true);
                        break;
                    /**设置设备名词 词性 == neq 0*/
                    case 3:
                        CustomDictWordUtils.setNatureAndFrequency(word,"neq 0",true);
                        break;
                    /**设置栖息地名词 词性 == nha 0*/
                    case 4:
                        CustomDictWordUtils.setNatureAndFrequency(word,"nha 0",true);
                        break;
                    /**设置场地名词 词性 == nve 0*/
                    case 5:
                        CustomDictWordUtils.setNatureAndFrequency(word,"nve 0",true);
                        break;
                    /**设置水产品幼体名词 词性 == napl 0*/
                    case 6:
                        CustomDictWordUtils.setNatureAndFrequency(word,"napl 0",true);
                        break;
                    default:
                        break;
                }
            }
            br.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
