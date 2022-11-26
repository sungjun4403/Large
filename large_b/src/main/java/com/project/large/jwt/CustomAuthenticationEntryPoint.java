package com.project.large.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws ServletException, IOException {
        String exception = (String) request.getAttribute("Authorization");
        String errorCode;

        if (exception.equals("Token Expired")) {
            errorCode = "Token Expired";
            setResponse(response, errorCode);
        }

        if (exception.equals("Token not Valid")) {
            errorCode = "Token not Valid";
            setResponse(response, errorCode);
        }
    }

    private void setResponse(HttpServletResponse response, String errorCode) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println("Authorization : " + errorCode);
    }

}
