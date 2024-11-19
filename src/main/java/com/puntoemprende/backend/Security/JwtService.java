/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.puntoemprende.backend.Security;

import com.puntoemprende.backend.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;


/**
 *
 * @author Ruisu's
 */
@Service
public class JwtService {
    
    @Value("${security.jwt.expiration-time}")
    private int EXPIRATION_TIME;
    
    @Value("${security.jwt.secret-word}")
    private String SECRET_WORD;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    public String generateKey(User user, Map<String, Object> extraClaims) throws NoSuchAlgorithmException {
        Date issueAt = new Date(System.currentTimeMillis());
        Date issueTo = new Date(issueAt.getTime() + (EXPIRATION_TIME * 60 * 60 * 1000));

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getEmail())
                .setIssuedAt(issueAt)
                .setExpiration(issueTo)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(SignatureAlgorithm.HS512, generateKey())
                .compact();
    }

    private Key generateKey() throws NoSuchAlgorithmException {
        byte[] keyByte = SECRET_WORD.getBytes(StandardCharsets.UTF_8);
        byte[] sha512 = MessageDigest.getInstance("SHA-512").digest(keyByte);
        return Keys.hmacShaKeyFor(sha512);
    }

    public String getName(String jwt) throws NoSuchAlgorithmException {
        return Jwts.parser()
                .setSigningKey(generateKey())
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String username) {
        try {
            var userDetails = userDetailsService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        } catch (Exception e) {
            return null;
        }
    }
}
