package org.example.springbootrestsecurity.controller;

import lombok.*;


@Getter
@Setter
public class AuthenticationRequestDTO {
    private String firstName;
    private String password;

}
