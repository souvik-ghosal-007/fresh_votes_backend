package com.souvik.fresh_votes.security;

import com.souvik.fresh_votes.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String username = userDetails.getUsername();
        String password = userDetails.getPassword();

        response.setStatus(HttpServletResponse.SC_OK);

        response.setContentType("application/json");

        response.getWriter().write(String.format("""
                {
                    "status":"success",
                    "data": {
                        "user": {
                            "username": "%s",
                            "password": "%s"
                        }
                    }
                }
                """, username, password));
    }
}