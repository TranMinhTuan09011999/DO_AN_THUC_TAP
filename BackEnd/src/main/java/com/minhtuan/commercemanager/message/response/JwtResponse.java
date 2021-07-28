package com.minhtuan.commercemanager.message.response;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String email;
    private String phone;
    private String address;
    private String firstname;
    private String lastname;
    private Integer gender;
    private Date birthday;
    private String role;
    private int status;
    private String message;
}
