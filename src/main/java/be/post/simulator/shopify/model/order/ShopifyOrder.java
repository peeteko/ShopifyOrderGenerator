package be.post.simulator.shopify.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ShopifyOrder {
    @JsonProperty("id")
    private String id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("name")
    private String name;
    @JsonProperty("total_weight")
    private Integer totalWeight;
    @JsonProperty("requires_shipping")
    private Boolean requiresShipping;
    @JsonProperty("shipping_address")
    private ShopifyShippingAddress shopifyShippingAddress;
    @JsonProperty("line_items")
    private List<ShopifyLineItem> shopifyLineItems;
    @JsonProperty("shipping_lines")
    private List<ShopifyShippingLine> shopifyShippingLines;
    @JsonProperty("customer")
    private ShopifyCustomer customer;

    public ShopifyCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(ShopifyCustomer customer) {
        this.customer = customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getRequiresShipping() {
        return requiresShipping;
    }

    public void setRequiresShipping(Boolean requiresShipping) {
        this.requiresShipping = requiresShipping;
    }

    public List<ShopifyLineItem> getShopifyLineItems() {
        return shopifyLineItems;
    }

    public void setShopifyLineItems(List<ShopifyLineItem> shopifyLineItems) {
        this.shopifyLineItems = shopifyLineItems;
    }

    public ShopifyShippingAddress getShopifyShippingAddress() {
        return shopifyShippingAddress;
    }

    public void setShopifyShippingAddress(ShopifyShippingAddress shopifyShippingAddress) {
        this.shopifyShippingAddress = shopifyShippingAddress;
    }

    public List<ShopifyShippingLine> getShopifyShippingLines() {
        return shopifyShippingLines;
    }

    public void setShopifyShippingLines(List<ShopifyShippingLine> shopifyShippingLines) {
        this.shopifyShippingLines = shopifyShippingLines;
    }

    public Integer getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Integer totalWeight) {
        this.totalWeight = totalWeight;
    }
}
