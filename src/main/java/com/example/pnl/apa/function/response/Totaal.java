package com.example.pnl.apa.function.response;

import com.example.pnl.util.JsonLocalDateTimeDateDeserializer;
import com.example.pnl.util.JsonLocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

public class Totaal {

    @JsonProperty("Aantal")
    protected int aantal;
    @JsonDeserialize(using = JsonLocalDateTimeDateDeserializer.class)
    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    @JsonProperty("Bezorgmoment")
    protected LocalDateTime bezorgmoment;

    /**
     * Gets the value of the aantal property.
     * 
     */
    public int getAantal() {
        return aantal;
    }

    /**
     * Sets the value of the aantal property.
     * 
     */
    public void setAantal(int value) {
        this.aantal = value;
    }

    /**
     * Gets the value of the bezorgmoment property.
     * 
     * @return
     *     possible object is
     *     {@link LocalDateTime }
     *     
     */
    public LocalDateTime getBezorgmoment() {
        return bezorgmoment;
    }

    /**
     * Sets the value of the bezorgmoment property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalDateTime }
     *     
     */
    public void setBezorgmoment(LocalDateTime value) {
        this.bezorgmoment = value;
    }
}
