package com.crackcode.zahangir.config;

import com.crackcode.zahangir.entity.User;
import com.crackcode.zahangir.service.LoginService;
import com.crackcode.zahangir.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final LoginService loginService;
    private final UserDetailsServiceImpl customUserDetailsService;

    public JwtAuthFilter(LoginService loginService, UserDetailsServiceImpl customUserDetailsService) {
        this.loginService = loginService;
        this.customUserDetailsService = customUserDetailsService;
    }
    private User getAuthenticatedUser(HttpServletRequest request){
        String authString = null;
        Enumeration<String> headerNameEnumeration = request.getHeaderNames();
        while (headerNameEnumeration.hasMoreElements()){
            String headerKey = headerNameEnumeration.nextElement();
            if(headerKey.equalsIgnoreCase("Authorization")){
                authString = request.getHeader(headerKey);
                break;
            }
        }
        return loginService.getLoginUser(authString, request);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        User user = getAuthenticatedUser(request);
        if(user != null){
            try {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getUsername());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        filterChain.doFilter(request, response);
    }
}

