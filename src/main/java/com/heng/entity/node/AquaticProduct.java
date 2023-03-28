package com.heng.entity.node;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.heng.entity.CommonNode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label = "水产品")
public class AquaticProduct extends CommonNode {
    /**
     * 别名
     */
    @Property(name = "别名")
    @JsonProperty(value = "别名")
    private String alias;

    /**
     * 特征
     */
    @Property(name = "特征")
    @JsonProperty(value = "特征")
    private String characteristic;

    /**
     * 栖息地
     */
    @Property(name = "栖息地")
    @JsonProperty(value = "栖息地")
    private String habitat;

    /**
     * 繁殖季节
     */
    @Property(name = "繁殖季节")
    @JsonProperty(value = "繁殖季节")
    private String breedingSeason;

    /**
     * 育苗场地
     */
    @Property(name = "育苗场地")
    @JsonProperty(value = "育苗场地")
    private String seedingPlace;

    /**
     * 饲养饵料
     */
    @Property(name = "饲养饵料")
    @JsonProperty(value = "饲养饵料")
    private String feeding;

    /**
     * 可能患有疾病
     */
    @Property(name = "可能患有疾病")
    @JsonProperty(value = "可能患有疾病")
    private String maySuffer;

    /**
     * 图片url
     */
    @Property(name = "图片")
    @JsonProperty(value = "图片")
    private String imgUrl;

    @Override
    public String toString() {
        return "别名\'" + alias + "\'。" +
                "它的特征是\'" + characteristic + "\'。" +
                "养殖时一般在\'" + seedingPlace + "\'育苗。" +
                "它的繁殖季节是\'" + breedingSeason + "\'。" +
                "栖息于\'" + habitat + "\'。" +
                "用\'" + feeding + "\'来饲养。" +
                "养殖过程中，它可能感染\'" + maySuffer + "\'等疾病，您可以通过查询疾病的相关信息进行判断和防治。" +
                "<br>以下是它的图片：<a target=\"_blank\" rel=\"noopener noreferrer\" href=\"" +
                imgUrl + "\"><img src=\"" + imgUrl + "\" title=\"点击查看大图\"></a>";
    }
}
