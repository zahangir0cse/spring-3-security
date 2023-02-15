package com.crackcode.zahangir.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class LoginDto {
    private String username;
    private String password;
}
