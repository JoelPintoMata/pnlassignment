package com.example.pnl.pakketten.model;

import java.sql.Timestamp;

/**
 * Pakketten model class
 */
public class Pakketten {

    private int totaal;
    private Timestamp totaalPostbusbezorgdDt;
    private int bbpx;
    private Timestamp bbpxPostbusbezorgdDt;
    private int agt;
    private Timestamp agtPostbusbezorgdDt;
    private String productType;

    public int getTotaal() {
        return totaal;
    }

    public void setTotaal(int totaal) {
        this.totaal = totaal;
    }

    public Timestamp getTotaalPostbusbezorgdDt() {
        return totaalPostbusbezorgdDt;
    }

    public void setTotaalPostbusbezorgdDt(Timestamp totaalPostbusbezorgdDt) {
        this.totaalPostbusbezorgdDt = totaalPostbusbezorgdDt;
    }

    public int getBbpx() {
        return bbpx;
    }

    public void setBbpx(int bbpx) {
        this.bbpx = bbpx;
    }

    public Timestamp getBbpxPostbusbezorgdDt() {
        return bbpxPostbusbezorgdDt;
    }

    public void setBbpxPostbusbezorgdDt(Timestamp bbpxPostbusbezorgdDt) {
        this.bbpxPostbusbezorgdDt = bbpxPostbusbezorgdDt;
    }

    public int getAgt() {
        return agt;
    }

    public void setAgt(int agt) {
        this.agt = agt;
    }

    public Timestamp getAgtPostbusbezorgdDt() {
        return agtPostbusbezorgdDt;
    }

    public void setAgtPostbusbezorgdDt(Timestamp agtPostbusbezorgdDt) {
        this.agtPostbusbezorgdDt = agtPostbusbezorgdDt;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
