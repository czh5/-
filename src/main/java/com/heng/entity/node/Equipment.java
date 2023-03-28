package com.heng.entity.node;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.heng.entity.CommonNode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@NodeEntity(label = "池塘设备")
public class Equipment extends CommonNode {

    /**o
     * 作用
     */
    @Property(name = "作用")
    @JsonProperty(value = "作用")
    private String function;

}
