package be.post.simulator.shopify.logging;

import org.slf4j.Logger;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class LoggingCustomizer implements RestTemplateCustomizer {

    private final Logger logger;

    public LoggingCustomizer(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void customize(RestTemplate restTemplate) {
        if (logger.isDebugEnabled()) {
            restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(restTemplate.getRequestFactory()));
            restTemplate.getInterceptors().add(new LoggingInterceptor(logger));
        }
    }
}
