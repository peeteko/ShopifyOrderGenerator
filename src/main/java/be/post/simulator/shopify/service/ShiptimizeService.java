package be.post.simulator.shopify.service;

import be.post.simulator.shopify.config.ShiptimizeServiceConfiguration;
import be.post.simulator.shopify.logging.LoggingCustomizer;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.apache.commons.codec.digest.HmacUtils;

import java.time.Duration;
import java.util.Collections;


@Component
public class ShiptimizeService {

    private static final String SHOPIFY_HMAC_HEADER = "X-Shopify-Hmac-Sha256";
    private static final String SHOPIFY_SHOP_DOMAIN_HEADER = "X-Shopify-Shop-Domain";

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiptimizeService.class);
    private final RestTemplate restTemplate;
    private ShiptimizeServiceConfiguration config;

    public ShiptimizeService(RestTemplateBuilder restTemplateBuilder, ShiptimizeServiceConfiguration shiptimizeServiceConfiguration) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(shiptimizeServiceConfiguration.getConnectTimeoutInMillis()))
                .setReadTimeout(Duration.ofMillis(shiptimizeServiceConfiguration.getReadTimeoutInMillis()))
                .additionalCustomizers(new LoggingCustomizer(LOGGER))
                .build();
        this.config = shiptimizeServiceConfiguration;
    }

    public void createOrder(String rawShopifyOrder) {
        try {
            String hmac = Base64.encodeBase64String(new HmacUtils(HmacAlgorithms.HMAC_SHA_256, config.getSecret().getBytes()).hmac(rawShopifyOrder));
            HttpEntity<String> request = new HttpEntity<>(rawShopifyOrder, getHeaders(hmac));
            ResponseEntity<String> response = this.restTemplate.exchange(
                    config.getWebhookUrl(), HttpMethod.POST, request, String.class);

            LOGGER.info("Call to " + config.getWebhookUrl() + " resulted in "+ String.valueOf(response.getStatusCode().value()));

        } catch (Exception e) {
            LOGGER.error("Call to " + config.getWebhookUrl() + " resulted in error", e);

        }
    }



    private HttpHeaders getHeaders(String hmac) {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(SHOPIFY_HMAC_HEADER, hmac);
        headers.set(SHOPIFY_SHOP_DOMAIN_HEADER, config.getShopName());

        headers.set("X-Shopify-Topic", "orders/create");
        headers.set("X-Shopify-API-Version", "2023/01");

        return headers;
    }


}

