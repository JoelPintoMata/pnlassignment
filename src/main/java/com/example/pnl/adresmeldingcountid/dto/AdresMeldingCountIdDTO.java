package com.example.pnl.adresmeldingcountid.dto;

/**
 * Address message dto
 */
public class AdresMeldingCountIdDTO {

    private Object bezorgDatum;
    private String postcode;
    private String huisnr;
    private String huisnrtvg;

    public Object getBezorgDatum() {
        return bezorgDatum;
    }

    public void setBezorgDatum(Object bezorgDatum) {
        this.bezorgDatum = bezorgDatum;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostCode(String postcode) {
        this.postcode = postcode;
    }

    public String getHuisnr() {
        return huisnr;
    }

    public void setHuisnr(String huisnr) {
        this.huisnr = huisnr;
    }

    public String getHuisnrtvg() {
        return huisnrtvg;
    }

    public void setHuisnrtvg(String huisnrtvg) {
        this.huisnrtvg = huisnrtvg;
    }
}
