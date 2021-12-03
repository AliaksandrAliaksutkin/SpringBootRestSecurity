package org.example.springbootrestsecurity.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequestDTO {
    private String firstName;
    private String password;

}
