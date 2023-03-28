package com.heng.entity.node;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.heng.entity.CommonNode;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;


@NodeEntity(label = "栖息地")
public class Habitat extends CommonNode {

}
