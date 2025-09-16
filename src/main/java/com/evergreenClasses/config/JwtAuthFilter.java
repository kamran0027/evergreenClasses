package com.evergreenClasses.config;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.evergreenClasses.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, java.io.IOException {

        // Get JWT from cookies
        String jwt = null;
        String email=null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("jwt".equals(cookie.getName())) {
                    jwt = cookie.getValue();
                }
            }
        }

        String path = request.getRequestURI();

        // Allow login and static resources without token
        if (path.equals("/login") || 
            path.equals("/logout") || 
            path.startsWith("/css") || 
            path.startsWith("/js") ||
            path.startsWith("/images") ||
            path.startsWith("/videos")|| 
            path.startsWith("/public")) {
            filterChain.doFilter(request, response);
            return;
        }

        // // If no token, redirect to login
        // if (jwt == null || jwt.isEmpty() || !jwtService.validateToken(jwt)) {
        //     response.sendRedirect("/login?error=unauthorized");
        //     return;
        // }

        if (jwt != null && jwtService.validateToken(jwt)) {
            email = jwtService.extractEmailId(jwt); // you must have this in JwtService

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 👉 No roles (authorities), so use empty list
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // ✅ Set authentication in security context
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } else {
            // ❌ No token or invalid token → redirect
            response.sendRedirect("/login?error=unauthorized");
            return;
        }

        // If valid token → continue request
        filterChain.doFilter(request, response);
    }
}

