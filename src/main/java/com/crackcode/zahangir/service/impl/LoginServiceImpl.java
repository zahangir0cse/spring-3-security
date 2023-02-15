package com.crackcode.zahangir.service.impl;

import com.crackcode.zahangir.entity.User;
import com.crackcode.zahangir.repository.UserRepository;
import com.crackcode.zahangir.service.LoginService;
import com.crackcode.zahangir.utils.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public User getLoginUser(String authString, HttpServletRequest request) {
        if(authString == null || authString.equals("")){
            return null;
        }
        String token = getToken(authString);
        if(jwtTokenProvider.isValidToken(token, request)){
            return userRepository.findById(jwtTokenProvider.getUserIdFromToken(token)).orElse(null);
        }
        return null;
    }

    private String getToken(String authString){
        String[] authParts = authString.split("\\s+");
        return authParts[1];
    }
}