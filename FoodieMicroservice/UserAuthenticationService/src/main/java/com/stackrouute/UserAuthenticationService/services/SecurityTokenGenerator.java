package com.stackrouute.UserAuthenticationService.services;

import com.stackrouute.UserAuthenticationService.model.User;

import java.util.Map;

public interface SecurityTokenGenerator {
    Map<String,String> generateToken(User user);
}
