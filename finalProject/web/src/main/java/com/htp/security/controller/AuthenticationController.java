package com.htp.security.controller;



import com.htp.security.model.AuthResponse;
import com.htp.security.model.AuthenticationRequest;
import com.htp.security.util.TokenUtil;
import com.htp.services.UserDetailsServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserDetailsServiceImpl userDetails;

    private final TokenUtil tokenUtil;

    private final AuthenticationManager authenticationManager;



    @ApiOperation(value = "Login user in system", notes = "Return Auth-Token with user login")
    @ApiResponses({
            @ApiResponse( code = 200, message = "Successful authorization"),
            @ApiResponse(code = 400, message = "Request error"),
            @ApiResponse(code = 500, message = "Server error")
    })
    @Transactional(rollbackFor = Exception.class)
    @PostMapping
    public ResponseEntity<AuthResponse> loginUser(@RequestBody @Valid AuthenticationRequest request){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return ResponseEntity.ok(
                AuthResponse
                .builder()
                .login(request.getUsername())
                .authToken(tokenUtil.generateToken(userDetails.loadUserByUsername(request.getUsername())))
                .build()
        );
    }
}
