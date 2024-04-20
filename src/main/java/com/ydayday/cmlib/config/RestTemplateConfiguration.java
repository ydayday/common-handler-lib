package com.ydayday.cmlib.config;

import com.ydayday.cmlib.utils.api.AbstractRestTemplate;
import com.ydayday.cmlib.utils.api.BaseRestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Slf4j
@AutoConfiguration
public class RestTemplateConfiguration {

    @Bean
    public HttpClient httpClient() {
        log.info(">>>>>>>> INIT BEAN : HttpClient");
        return HttpClientBuilder.create()
                .setMaxConnTotal(100)
                .setMaxConnPerRoute(30)
                .build();
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(HttpClient httpClient) {
        log.info(">>>>>>>> INIT BEAN : HttpComponentsClientHttpRequestFactory");
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(3000);
        factory.setHttpClient(httpClient);
        return factory;
    }

    @Bean
    public RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory factory) {
        log.info(">>>>>>>> INIT BEAN : RestTemplate");
        return new RestTemplate(factory);
    }

    @Bean
    public BaseRestTemplate baseRestTemplate() {
        log.info(">>>>>>>> INIT BEAN : BaseRestTemplate");
        return new BaseRestTemplate(
                restTemplate (
                        httpComponentsClientHttpRequestFactory(httpClient())
                )
        );
    }

}
