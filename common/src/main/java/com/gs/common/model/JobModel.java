package com.gs.common.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class JobModel extends PersistenceModel {

    private String jobUrl;
    private String query;

    @SerializedName(value = "title")
    String title;
    @SerializedName(value = "createdOn")
    String createdOn;
    @SerializedName(value = "type")
    Integer type;
    @SerializedName(value = "ciphertext")
    String ciphertext;
    @SerializedName(value = "description")
    String description;
    @SerializedName(value = "category2")
    String category2;
    @SerializedName(value = "subcategory2")
    String subcategory2;
    @SerializedName(value = "skills")
    List<SkillModel> skills;
    String uid;
    String duration;
    String shortDuration;
    String durationLabel;
    String engagement;
    String shortEngagement;
    AmountModel amount;
    Integer recno;
    ClientModel client;
    Integer freelancersToHire;
    String relevanceEncoded;
    Boolean enterpriseJob;
    String tierText;
    String tier;
    String tierLabel;
    String isSaved;
    String feedback;
    String proposalsTier;
    Boolean isApplied;
    Boolean sticky;
    String stickyLabel;
    String jobTs;
    Boolean prefFreelancerLocationMandatory;
    Boolean premium;
    Integer plusBadge;
    String publishedOn;
    Object renewedOn;
    Object sandsService;
    Object sandsSpec;
    Object sandsAttrs;
    Boolean isLocal;
    Object workType;
    Object locations;
    Object hourlyBudgetText;
    //Object prefFreelancerLocation;
    @SerializedName(value = "attrs")
    List<AttributeModel> attributes;
}
