package com.heng.entity.node;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.heng.entity.CommonNode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@NodeEntity(label = "饲养饵料")
public class Feed extends CommonNode {
    /**
     * 营养成分
     */
    @Property(name = "营养成分")
    @JsonProperty(value = "营养成分")
    private String nutrient;

}
