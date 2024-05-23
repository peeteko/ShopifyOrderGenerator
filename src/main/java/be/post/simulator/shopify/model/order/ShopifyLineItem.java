package be.post.simulator.shopify.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShopifyLineItem {
    @JsonProperty("grams")
    private Integer grams;

    public Integer getGrams() {
        return grams;
    }

    public void setGrams(Integer grams) {
        this.grams = grams;
    }
}
