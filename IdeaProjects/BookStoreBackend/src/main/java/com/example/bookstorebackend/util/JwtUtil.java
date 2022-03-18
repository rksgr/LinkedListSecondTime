package com.example.bookstorebackend.util;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "secretkey";

    // Method to generate token containing emailId as claim and using HMAC512 algorithm
    public String generateToken(String emailId) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(SECRET_KEY);

            String jwtToken = JWT.create()
                                .withClaim("emailId", emailId)
                                .sign(algorithm);
            return jwtToken;
        } catch (JWTCreationException e) {
            e.printStackTrace();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Returns the emailId extracted from the token
    public String decodeToken(String jwtToken) {

        String emailId;
        Verification verifyToken = null;

        try {
            verifyToken = JWT.require(Algorithm.HMAC512(SECRET_KEY));
        } catch(IllegalArgumentException e) {
            e.printStackTrace();
        }

        JWTVerifier verifier = verifyToken.build();

        DecodedJWT decodedJwt = verifier.verify(jwtToken);

        Claim claim = decodedJwt.getClaim("emailId");

        emailId = claim.asString();
        return emailId;
    }
}
