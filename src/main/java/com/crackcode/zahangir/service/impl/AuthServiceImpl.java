package com.crackcode.zahangir.service.impl;

import com.crackcode.zahangir.dto.LoginDto;
import com.crackcode.zahangir.dto.LoginResponseDto;
import com.crackcode.zahangir.dto.Response;
import com.crackcode.zahangir.dto.UserPrincipal;
import com.crackcode.zahangir.repository.UserRepository;
import com.crackcode.zahangir.service.AuthService;
import com.crackcode.zahangir.utils.JwtTokenProvider;
import com.crackcode.zahangir.utils.ResponseBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("authService")
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Response login(LoginDto loginDto, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            if (authentication.isAuthenticated()){
                UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
                String token = jwtTokenProvider.generateToken(authentication, request);
                LoginResponseDto dto = new LoginResponseDto();
                dto.setToken(token);
//                dto.setId(principal.getId());
                dto.setUsername(principal.getUsername());
                dto.setAuthorities(principal.getAuthorities());
                return ResponseBuilder.getSuccessResponse(HttpStatus.OK, dto, "Success");
            }
            return ResponseBuilder.getFailResponse(HttpStatus.UNAUTHORIZED, "Invalid");
        } catch (Exception e) {
            log.error(this.getClass().getName() + " " + new Throwable().getStackTrace()[0].getMethodName() + " " + e.getMessage());
            return ResponseBuilder.getFailResponse(HttpStatus.NOT_FOUND, "Failed");
        }
    }

    @Override
    public Response logout(HttpSession session) {
        SecurityContextHolder.getContext().setAuthentication(null);
        session.invalidate();
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, null, "Success");
    }
}
