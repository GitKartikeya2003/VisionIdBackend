package com.example.VisionIdBackend.service.impl;

import com.example.VisionIdBackend.service.IJwtService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



@Service
public class JwtServiceImpl implements IJwtService {

    private String secretKey;

    public JwtServiceImpl() {
        secretKey = generateSecretKey();
    }

    private static String generateSecretKey(){
        try{

            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");


            SecretKey secretKey = keyGen.generateKey();
            System.out.println("Secret Key: " + secretKey.toString());

            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }
        catch (Exception e){

            throw new RuntimeException("Secret Key generation failed");
        }
    }

    @Override
    public String generateToken(String uid) {
        Map<String,Object> claims = new HashMap<>(); //Claims can include custom data(like roles and permissions)
    claims.put("uid",uid);

    return Jwts.builder()
            .setClaims(claims)
            .setSubject(uid)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*3))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();

    }

    private Key getKey(){

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);

    }


}
