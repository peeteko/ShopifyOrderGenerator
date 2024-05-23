package be.post.simulator.shopify.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShopifyCustomer{

        @JsonProperty("phone")
        private String phone;
        @JsonProperty("email")
        private String email;

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }
}
