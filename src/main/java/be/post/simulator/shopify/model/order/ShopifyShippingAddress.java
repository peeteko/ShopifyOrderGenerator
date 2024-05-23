package be.post.simulator.shopify.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShopifyShippingAddress {
    @JsonProperty("first_name")
    String firstName;
    @JsonProperty("last_name")
    String lastName;
    @JsonProperty("name")
    String name;
    @JsonProperty("address1")
    String address1;
    @JsonProperty("address2")
    String address2;
    @JsonProperty("city")
    String city;
    @JsonProperty("zip")
    String zip;
    @JsonProperty("country")
    String country;
    @JsonProperty("country_code")
    String countryCode;
    @JsonProperty("company")
    String company;
}

