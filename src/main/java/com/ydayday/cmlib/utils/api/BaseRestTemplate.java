package com.ydayday.cmlib.utils.api;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class BaseRestTemplate extends AbstractRestTemplate {

    public BaseRestTemplate(RestTemplate restTemplate) {
        super(restTemplate);
    }

    /**
     * <pre>
     * 1. 개요 : GET 요청
     * 2. 처리내용 : 기본 GET 요청 처리
     * </pre>
     */
    public <S> S GET(String uri, Class<S> returnClassName) {
        RequestEntity<?> requestEntity = RequestEntity.get(uri)
                .headers(getJsonHeader())
                .build();

        return super.get(requestEntity, returnClassName).getBody();
    }

    /**
     * <pre>
     * 1. 개요 : POST 요청
     * 2. 처리내용 : 기본 POST 요청 처리
     * </pre>
     */
    public <T,S> S POST(String uri, T body, Class<S> returnClassName) {
        RequestEntity<T> requestEntity = RequestEntity.post(uri)
                .headers(getJsonHeader())
                .body(body);

        return super.post(requestEntity, returnClassName).getBody();
    }

    /**
     * <pre>
     * 1. 개요 : PUT 요청
     * 2. 처리내용 : 기본 PUT 요청 처리
     * </pre>
     */
    public <T,S> S PUT(String uri, T body, Class<S> returnClassName) {
        RequestEntity<T> requestEntity = RequestEntity.put(uri)
                .headers(getJsonHeader())
                .body(body);

        return super.put(requestEntity, returnClassName).getBody();
    }

    /**
     * <pre>
     * 1. 개요 : DELETE 요청
     * 2. 처리내용 : Body 전송이 가능한 DELETE 요청 처리
     * </pre>
     */
    public <T,S> S DELETE(String uri, T body, Class<S> returnClassName) {
        RestTemplate rest = new RestTemplate(new SimpleClientHttpRequestWithBodyFactory());
        HttpHeaders headers = getJsonHeader();

        return rest.exchange(uri, HttpMethod.DELETE, new HttpEntity<>(body, headers), returnClassName).getBody();
    }

}
