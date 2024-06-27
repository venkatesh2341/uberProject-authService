package com.uber_project_auth_service.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService  {

    @Value("${jwt.expiry}")
    private int expiry;

    @Value("${jwt.secret}")
    private String SECRET;

    public SecretKey getSignInKey()
    {
        return  Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(Map<String, Object> payload, String email)
    {
        Date now= new Date();
        Date expirtyDate= new Date(now.getTime()+ expiry* 1000L);

        return Jwts.builder()
                .claims(payload)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expirtyDate)
                .subject(email)
                .signWith(getSignInKey())
                .compact();
    }
    public String createToken(String email){
        return createToken(new HashMap<>(),email);
    }

    public Claims extractAllClaims(String token) {
        return  Jwts
                .parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Object extractPayloadWithKey(String token, String payloadKey){
       Claims claims= extractAllClaims(token);
       return (Object) claims.get(payloadKey);
    }

    public String extractPhoneNumber(String token){
        Claims claims = extractAllClaims(token);
        return (String) claims.get("phoneNumber");
    }
    public boolean isTokenValid(String token, String email)
    {
        return !isTokenExpired(token) && extractPayloadWithKey(token, email).equals(email);
    }

//    @Override
//    public void run(String... args) throws Exception {
//
//        Map<String, Object> payload= new HashMap<>();
//        payload.put("email","venky@gmail.com");
//        payload.put("password","venky123");
//        payload.put("phoneNumber","888888888");
//        String token = createToken(payload,payload.get("email").toString());
//        System.out.println(token);
//        System.out.println(extractPhoneNumber(token));
//        System.out.println(extractPayloadWithKey(token,"password"));
//    }
}
