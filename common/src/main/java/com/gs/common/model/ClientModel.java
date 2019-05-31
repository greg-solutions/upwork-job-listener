package com.gs.common.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClientModel implements Serializable {

    String paymentVerificationStatus;
    LocationModel loc;
    Double totalSpent;
    Double totalReviews;
    Double totalFeedback;
    Integer companyRid;
    Integer companyName;
    Integer edcUserId;
    Integer lastContractPlatform;
    Integer lastContractRid;
    Integer lastContractTitle;
    String feedbackText;
    String companyOrgUid;
}
