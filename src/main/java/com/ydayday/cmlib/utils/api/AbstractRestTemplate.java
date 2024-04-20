package com.ydayday.cmlib.utils.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractRestTemplate {

    private final RestTemplate restTemplate;

    /**
     * <pre>
     * 1. 개요 : GET 요청
     * 2. 처리내용 : GET 요청을 처리한다.
     * </pre>
     *
     * @Method Name : get
     * @author : ydayday
     */
    public <T, S> ResponseEntity<S> get(RequestEntity<T> entity, Class<S> returnClassName) {
        ResponseEntity<S> exchange = restTemplate.exchange(entity, returnClassName);
        printLoggingMsg(entity.getMethod().toString(), entity.toString(), null, exchange.getBody());
        return exchange;
    }

    /**
     * <pre>
     * 1. 개요 : POST 요청
     * 2. 처리내용 : POST 요청을 처리한다.
     * </pre>
     *
     * @Method Name : post
     * @date : 2024. 3. 20.
     * @author : ydayday
     * @history :
     * ----------------------------------------------------------------------------------
     * 변경일 작성자 변경내역
     * -------------- -------------- ----------------------------------------------------
     * 2024. 3. 20. insung 최초작성
     * ----------------------------------------------------------------------------------
     */
    public <T, S> ResponseEntity<S> post(RequestEntity<T> entity, Class<S> returnClassName) {
        ResponseEntity<S> exchange = restTemplate.exchange(entity, returnClassName);
        printLoggingMsg(entity.getMethod().toString(), entity.toString(), entity.getBody(), exchange.getBody());
        return restTemplate.exchange(entity, returnClassName);
    }

    public <T, S> ResponseEntity<S> put(RequestEntity<T> entity, Class<S> returnClassName) {
        ResponseEntity<S> exchange = restTemplate.exchange(entity, returnClassName);
        printLoggingMsg(entity.getMethod().toString(), entity.toString(), entity.getBody(), exchange.getBody());
        return restTemplate.exchange(entity, returnClassName);
    }

    /**
     * <pre>
     * 1. 개요 : HTTP 헤더 설정
     * 2. 처리내용 : JSON 형식의 헤더 생성
     * </pre>
     *
     * @Method Name : getJsonHeader
     * @author : ydayday
     */
    protected static HttpHeaders getJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(MediaType.parseMediaTypes(MediaType.APPLICATION_JSON_VALUE));
        return headers;
    }

    public static <T,S> void printLoggingMsg(String method, String uri, T request, S response) {
        log.info("");
        log.info("====================== REST TEMPLATE START  ======================");
        log.info(" API METHOD : {} ", method);
        log.info(" REQUEST URI : {} ", uri);
        if (Objects.nonNull(request)) {
            log.info(" REQUEST BODY : {} ", request);
        }
        log.info(" RESPONSE BODY : {} ", response);
        log.info("====================== REST TEMPLATE END  ========================");
        log.info("");
    }

}