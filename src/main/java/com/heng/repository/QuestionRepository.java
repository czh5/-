package com.heng.repository;

import com.heng.entity.node.AquaticProduct;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;


/**
 * 基于水产品知识图谱的问答查询接口
 * @Author heng
 * @Date 2023/3/19 19:22
 * @version 1.0
 */
public interface QuestionRepository extends Neo4jRepository<AquaticProduct,Long> {

    //问题模板0:nap 别名
    @Query("match (n:水产品) where n.name={name} return n.别名")
    String getAquaticProductsCommonName(@Param("name") String name);

    //问题模板1:nap 可能患有疾病
    @Query("match (n:水产品) where n.name={name} return n.可能患有疾病")
    String getAquaticProductsDisease(@Param("name") String name);

    //问题模板2:nap 图片
    @Query("match (n:水产品) where n.name={name} return n.图片")
    String getAquaticProductsPicture(@Param("name") String name);

    //问题模板3:nap 栖息地
    @Query("match (n:水产品) where n.name={name} return n.栖息地")
    String getAquaticProductsHabitat(@Param("name") String name);

    //问题模板4:nap 特征
    @Query("match (n:水产品) where n.name={name} return n.特征")
    String getAquaticProductsFeatures(@Param("name") String name);

    //问题模板5:nap 繁殖季节
    @Query("match (n:水产品) where n.name={name} return n.繁殖季节")
    String getAquaticProductsBreedingSeason(@Param("name") String name);

    //问题模板6:nap 育苗场地
    @Query("match (n:水产品) where n.name={name} return n.育苗场地")
    String getAquaticProductsVenue(@Param("name") String name);

    //问题模板7:nap 饲养饵料
    @Query("match (n:水产品) where n.name={name} return n.饲养饵料")
    String getAquaticProductsBait(@Param("name") String name);

    //问题模板8:nba 营养成分
    @Query("match (n:饲养饵料) where n.name={name} return n.营养成分")
    String getBaitNutrients(@Param("name") String name);

    //问题模板9:ndi 病因
    @Query("match (n:疾病) where n.name={name} return n.病因")
    String getDiseasePathogen(@Param("name") String name);

    //问题模板10:ndi 症状
    @Query("match (n:疾病) where n.name={name} return n.症状")
    String getDiseaseSymptom(@Param("name") String name);

    //问题模板11:ndi 防治方法
    @Query("match (n:疾病) where n.name={name} return n.防治方法")
    String getDiseasePrevention(@Param("name") String name);

    //问题模板12:neq 作用
    @Query("match (n:池塘设备) where n.name={name} return n.作用")
    String getEquipmentFunction(@Param("name") String name);

    //问题模板13:nve 池塘设备
    @Query("match (n:育苗场地) where n.name={name} return n.池塘设备")
    String getVenueEquipment(@Param("name") String name);

    //问题模板14:nve 池深
    @Query("match (n:育苗场地) where n.name={name} return n.池深")
    String getVenueDepth(@Param("name") String name);

    //问题模板15:nve 面积
    @Query("match (n:育苗场地) where n.name={name} return n.面积")
    String getVenueArea(@Param("name") String name);

    //问题模板16:napl 成长
    @Query("match (n:水产品幼体) where n.name={name} return n.成长")
    String getAquaticProductsLarvaeGrow(@Param("name") String name);

    //问题模板17:napl 饲养饵料
    @Query("match (n:水产品幼体) where n.name={name} return n.饲养饵料")
    String getAquaticProductsLarvaeBait(@Param("name") String name);

    //问题模板18:养殖于 nve
    @Query("match (n)-[:养殖于]->(m:育苗场地) where m.name={name} return n.name")
    String[] getSthToVenue(@Param("name") String name);

    //问题模板19:可能患有 ndi
    @Query("match (n)-[:可能患有疾病]->(m:疾病) where m.name={name} return n.name")
    String[] getSthToDisease(@Param("name") String name);

    //问题模板20:成长为 nap
    @Query("match (n)-[:成长]->(m:水产品) where m.name={name} return n.name")
    String[] getSthToAquaticProducts(@Param("name") String name);

    //问题模板21:成长为 napl
    @Query("match (n)-[:成长]->(m:水产品幼体) where m.name={name} return n.name")
    String[] getSthToAquaticProductsLarvae(@Param("name") String name);

    //问题模板22:投喂 nba
    @Query("match (n)-[:投喂]->(m:饲养饵料) where m.name={name} return n.name")
    String[] getSthToBait(@Param("name") String name);

    //问题模板23:栖息于 nha
    @Query("match (n)-[:栖息于]->(m:栖息地) where m.name={name} return n.name")
    String[] getSthToHabitat(@Param("name") String name);

    //问题模板24:需要配备 neq
    @Query("match (n)-[:需要配备]->(m:池塘设备) where m.name={name} return n.name")
    String[] getSthToEquipment(@Param("name") String name);

    //问题模板25:nap 全部信息
    @Query("match (n:水产品) where n.name={name} return n")
    AquaticProduct getAquaticProductsInfo(@Param("name") String name);
}
