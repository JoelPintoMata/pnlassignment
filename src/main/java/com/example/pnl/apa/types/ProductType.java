package com.example.pnl.apa.types;

public enum ProductType {
    PRODUCT_AGT("AGT"),
    PRODUCT_BBPX("BBPX"),
    PRODUCT_PKT("Pakketten"),
    ;

    private final String value;

    ProductType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
