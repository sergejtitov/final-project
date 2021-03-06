package com.htp.security.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Slf4j
@Data
@Builder
public class AuthResponse {
    @Email
    @Size(min = 1, max = 100)
    private String login;

    @Size(min = 8, max = 100)
    private String authToken;
}
