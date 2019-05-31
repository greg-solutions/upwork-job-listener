package com.gs.common.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AmountModel implements Serializable {
    String currencyCode;
    String amount;
}
