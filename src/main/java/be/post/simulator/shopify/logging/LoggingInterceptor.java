package be.post.simulator.shopify.logging;

import org.slf4j.Logger;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static org.springframework.util.StreamUtils.copyToByteArray;

public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static final List<MediaType> MEDIA_TYPES_TO_SKIP = List.of(
            MediaType.APPLICATION_PDF,
            MediaType.APPLICATION_OCTET_STREAM
    );

    private final Logger logger;

    public LoggingInterceptor(Logger logger) {
        this.logger = logger;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] requestBody, ClientHttpRequestExecution requestExecution) throws IOException {

        if (logger.isDebugEnabled()) {
            logger.debug(formatRequest(request, requestBody));
        }

        ClientHttpResponse response = requestExecution.execute(request, requestBody);

        if (logger.isDebugEnabled()) {
            logger.debug(formatResponse(response));
        }

        return response;
    }

    private String formatRequest(HttpRequest request, byte[] requestBody) {
        String formattedBody = new String(requestBody, getCharset(request));
        return String.format("Request: %s %s %s", request.getMethod(), request.getURI(), formattedBody);
    }

    private String formatResponse(ClientHttpResponse response) throws IOException {
        String formattedBody = getFormattedBody(response);
        return String.format("Response: %s %s", response.getStatusCode().value(), formattedBody);
    }

    private String getFormattedBody(ClientHttpResponse response) throws IOException {
        MediaType contentType = response.getHeaders().getContentType();
        if (shouldSkipMediaType(contentType)) {
            return "Body not printed for content-type " + contentType;
        }
        return new String(copyToByteArray(response.getBody()), getCharset(response));
    }

    protected Charset getCharset(HttpMessage message) {
        return Optional.ofNullable(message.getHeaders().getContentType())
                .map(MediaType::getCharset)
                .orElse(DEFAULT_CHARSET);
    }

    private boolean shouldSkipMediaType(MediaType mediaType) {
        if (mediaType == null) {
            return false;
        }
        return MEDIA_TYPES_TO_SKIP.stream().anyMatch(mediaType::isCompatibleWith);
    }
}