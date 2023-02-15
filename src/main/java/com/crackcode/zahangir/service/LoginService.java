package com.crackcode.zahangir.service;

import com.crackcode.zahangir.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public interface LoginService {
    User getLoginUser(String authString, HttpServletRequest request);
}
