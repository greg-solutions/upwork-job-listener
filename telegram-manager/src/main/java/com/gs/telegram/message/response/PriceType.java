package com.gs.telegram.message.response;

public enum PriceType {
    FIX_PRICE("Fixed-price"),
    HOURLY("Hourly");

    private String name;

    PriceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
