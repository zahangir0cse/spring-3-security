package com.crackcode.zahangir.service;

import com.crackcode.zahangir.dto.LoginDto;
import com.crackcode.zahangir.dto.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public interface AuthService {
    Response login(LoginDto loginDto, HttpServletRequest request);

    Response logout(HttpSession session);
}
