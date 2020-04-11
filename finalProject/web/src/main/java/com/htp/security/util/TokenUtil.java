package com.htp.security.util;

import com.htp.security.config.JwtTokenConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Calendar;

import static io.jsonwebtoken.Claims.SUBJECT;


@Component
@RequiredArgsConstructor
public class TokenUtil {
    public static final String CREATED = "created";
    public static final String ROLES = "roles";


    private final JwtTokenConfig jwtTokenConfig;

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(SUBJECT, userDetails.getUsername());
        claims.put(CREATED, generateCurrentDate());
        claims.put(ROLES, getEncryptedRoles(userDetails));
        return generateToken(claims);
    }

    private Date generateCurrentDate(){
        return new Date();
    }

    private List<String> getEncryptedRoles(UserDetails userDetails){
        return userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(s -> s.replace("ROLE_", ""))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
    }

    private String generateToken (Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, jwtTokenConfig.getSecret())
                .compact();
    }

    private Date generateExpirationDate(){
        Calendar calendar =  Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND, jwtTokenConfig.getExpire());
        return calendar.getTime();
    }

    public String getUsernameFromToken (String token){
        return getClaimsFromToken(token).getSubject();
    }

    public Claims getClaimsFromToken(String token){
        return  Jwts
                .parser()
                .setSigningKey(jwtTokenConfig.getSecret())
                .parseClaimsJws(token)
                .getBody();
    }

    public Date getCreatedDateFromToken (String token){
        return(Date) getClaimsFromToken(token).get(CREATED);
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimsFromToken(token).getExpiration();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername());
    }
}
