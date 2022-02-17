package com.stackrouute.UserAuthenticationService.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class User {
    @Id
    private String email;
    private String password;
    private String accountType;
}
