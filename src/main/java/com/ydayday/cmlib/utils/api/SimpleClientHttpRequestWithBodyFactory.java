package com.ydayday.cmlib.utils.api;

import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.io.IOException;
import java.net.HttpURLConnection;

public class SimpleClientHttpRequestWithBodyFactory extends SimpleClientHttpRequestFactory {

    /**
     * Custom Delete Method - Body 값 전송 허용
     * @param connection the connection to prepare
     * @param httpMethod the HTTP request method ({@code GET}, {@code POST}, etc.)
     * @throws IOException
     */
    @Override
    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
        super.prepareConnection(connection, httpMethod);
        if ("DELETE".equals(httpMethod)) {
            connection.setDoOutput(true);
        }
    }

}
