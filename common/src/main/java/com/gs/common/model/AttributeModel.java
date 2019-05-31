package com.gs.common.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AttributeModel implements Serializable {
    String parentSkillUid;
    Integer skillType;
    String uid;
    Boolean highlighted;
    String prettyName;
    String name;
}
