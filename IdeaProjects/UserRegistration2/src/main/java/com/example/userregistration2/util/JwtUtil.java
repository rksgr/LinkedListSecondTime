package com.example.userregistration2.util;

import com.example.userregistration2.model.JwtRequest;
import com.example.userregistration2.model.JwtResponse;
import com.example.userregistration2.service.CustomUserDataService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final long serialVersionUID = -2550185165626007844L;
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 5;

    private String SECRET_KEY = "altafhussainhussainaltaf";



    //retrieve username from jwt token
    public String getUserNameFromToken(String token)
    {
        return getClaimFromToken(token, Claims::getSubject);

    }
    // Retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token)
    {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
    {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    // For retrieving any information from token we will
    // need the secret key
    private Claims getAllClaimsFromToken(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // check if the token has expired
    private Boolean isTokenExpired(String token)
    {
        //final Date expiration = getExpirationDateFromToken(token);
        return getExpirationDateFromToken(token).before(new Date());
    }

    //Generate token for user
    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return doGenerateToken(claims,userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    // Validate Token
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername())  &&
                !isTokenExpired(token));
    }
}
