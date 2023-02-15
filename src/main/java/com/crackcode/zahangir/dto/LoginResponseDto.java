package com.crackcode.zahangir.dto;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class LoginResponseDto {
    private String username;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;
}
