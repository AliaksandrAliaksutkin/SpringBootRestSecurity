package org.example.springbootrestsecurity.controller;

import lombok.*;


//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class AuthenticationRequestDTO {
    private String firstName;
    private String password;

}
