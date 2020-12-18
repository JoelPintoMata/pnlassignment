package com.example.pnl.apa.function.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ApaResponse {

    @JsonProperty(value = "Totaal", required = true)
    private Totaal totaal;
    @JsonProperty("Product")
    private List<Product> products;

    /**
     * Gets the value of the totaal property.
     *
     * @return
     *     possible object is
     *     {@link Totaal }
     *
     */
    public Totaal getTotaal() {
        return totaal;
    }

    /**
     * Sets the value of the totaal property.
     *
     * @param value
     *     allowed object is
     *     {@link Totaal }
     *
     */
    public void setTotaal(Totaal value) {
        this.totaal = value;
    }

    /**
     * Gets the value of the product property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the product property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProduct().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Product }
     *
     *
     */
    public List<Product> getProducts() {
        if (products == null) {
            products = new ArrayList<>();
        }
        return new ArrayList<>(this.products);
    }

    /**
     * Sets the value of the product property.
     *
     * @param products a
     *     allowed object is
     *     {@link Product }
     *
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Adds a new value to the product property.
     * @param product
     *     allowed object is
     *     {@link Product }
     */
    public void addProduct(Product product) {
        if (this.products == null) {
            this.products = new ArrayList<>(1);
        }
        this.products.add(product);
    }
}
