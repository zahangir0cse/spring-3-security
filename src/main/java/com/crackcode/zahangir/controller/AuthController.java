package com.crackcode.zahangir.controller;

import com.crackcode.zahangir.annotations.ApiController;
import com.crackcode.zahangir.dto.LoginDto;
import com.crackcode.zahangir.dto.Response;
import com.crackcode.zahangir.service.AuthService;
import com.crackcode.zahangir.utils.UrlConstraint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@ApiController
@RequestMapping(UrlConstraint.AuthManagement.ROOT)
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(UrlConstraint.AuthManagement.LOGIN)
    public Response login(@RequestBody @Validated LoginDto loginDto, BindingResult result, HttpServletRequest request, HttpServletResponse response){
        return authService.login(loginDto, request);
    }

    @PostMapping(UrlConstraint.AuthManagement.LOGOUT)
    public Response logout(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        return authService.logout(session);
    }
}
