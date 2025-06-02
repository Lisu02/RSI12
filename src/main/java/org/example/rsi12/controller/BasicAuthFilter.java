package org.example.rsi12.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;

@Component
public class BasicAuthFilter implements Filter {

    private static final String EXPECTED_USERNAME = "admin";
    private static final String EXPECTED_PASSWORD = "secret";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;

        String authHeader = httpReq.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            httpRes.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpRes.setHeader("WWW-Authenticate", "Basic realm=\"Restricted\"");
            return;
        }

        // Odczyt i dekodowanie danych logowania
        String base64Credentials = authHeader.substring("Basic ".length());
        String credentials = new String(Base64.getDecoder().decode(base64Credentials));
        String[] values = credentials.split(":", 2);

        if (values.length < 2 || !EXPECTED_USERNAME.equals(values[0]) || !EXPECTED_PASSWORD.equals(values[1])) {
            httpRes.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        chain.doFilter(request, response);
    }
}

