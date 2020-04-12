package com.htp.security.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;

@Slf4j
public class PrincipalUtils {

    public static String getUsername(Principal principal){
        Object castedPrincipal = ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return ((User) castedPrincipal).getUsername();
    }

}
