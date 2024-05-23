package be.post.simulator.shopify.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="shiptimize")
public class ShiptimizeServiceConfiguration {

    private String webhookUrl;
    private String secret;
    private String shopName;
    private int connectTimeoutInMillis;
    private int readTimeoutInMillis;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    public int getConnectTimeoutInMillis() {
        return connectTimeoutInMillis;
    }

    public void setConnectTimeoutInMillis(int connectTimeoutInMillis) {
        this.connectTimeoutInMillis = connectTimeoutInMillis;
    }

    public int getReadTimeoutInMillis() {
        return readTimeoutInMillis;
    }

    public void setReadTimeoutInMillis(int readTimeoutInMillis) {
        this.readTimeoutInMillis = readTimeoutInMillis;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
