package com.vpos.server.utils;

/*
 * @created 11/04/2023 - 1:49 PM
 * @project server
 * @author Rithy SKUN
 */

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;


public class HttpLogger extends HttpServletRequestWrapper {

    private byte[] payload;
    public HttpLogger(HttpServletRequest request) throws IOException {
        super(request);
        InputStream requestInputStream = request.getInputStream();
        this.payload = StreamUtils.copyToByteArray(requestInputStream);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return super.getInputStream();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return super.getReader();
    }


}
