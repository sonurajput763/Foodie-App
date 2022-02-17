package com.stackrouute.UserAuthenticationService.services;

import com.stackrouute.UserAuthenticationService.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator{
    @Override
    public Map<String, String> generateToken(User user) {
        String jwtToken;
        if(user.getAccountType().equalsIgnoreCase("customer"))
        {
            jwtToken= Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"customerkey").compact();
        }
        else
        {
            jwtToken= Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"vendorkey").compact();
        }
        Map<String,String> map=new HashMap<>();
        map.put("Token",jwtToken);
        map.put("message","Authentication Successful");

        return map;
    }
}
