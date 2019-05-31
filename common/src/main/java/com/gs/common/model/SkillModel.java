package com.gs.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class SkillModel implements Serializable {

    @SerializedName(value = "name")
    String name;
    @SerializedName(value = "prettyName")
    String prettyName;
    @SerializedName(value = "highlighted")
    String highlighted;
    @SerializedName(value = "uid")
    String uid;


}
