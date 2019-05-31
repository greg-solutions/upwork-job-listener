package com.upwork.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class SkillModel {

    @SerializedName(value = "name")
    String name;
    @SerializedName(value = "prettyName")
    String prettyName;
    @SerializedName(value = "highlighted")
    String highlighted;
    @SerializedName(value = "uid")
    String uid;


}
