package be.post.simulator.shopify.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShopifyShippingLine {
    @JsonProperty("title")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
