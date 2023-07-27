package com.hairsalon.security.jwt;

import com.hairsalon.constants.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    @Autowired
    Message message;
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationEntryPointHandler.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException, ServletException, UsernameNotFoundException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        String messageResult = "";
        String statusResult = "";
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        if(authException instanceof BadCredentialsException) {
            messageResult = message.getMessageByItemCode("LOGINE1");
            statusResult = "ERROR";
        }
        else {
            messageResult = authException.getMessage();
            statusResult = String.valueOf(HttpServletResponse.SC_UNAUTHORIZED);
        }

        PrintWriter out = response.getWriter();
        out.println("{ \"status\": \"" + statusResult + "\", ");
        out.println("\"message\": \"" + messageResult + "\" }");

    }
}