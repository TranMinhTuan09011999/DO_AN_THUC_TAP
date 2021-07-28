package com.minhtuan.commercemanager.message.request;

import com.minhtuan.commercemanager.model.Role;
import com.minhtuan.commercemanager.validation.email.ValidEmail;
import lombok.*;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class SignupRequest {

    @NotBlank(message = "password is required")
    @ValidEmail
    private String email;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "role is required")
    private String role;

    @NotBlank(message = "firstname is required")
    private String firstname;

    @NotBlank(message = "lastname is required")
    private String lastname;

    private Integer gender;

    private Date birthday;

    @NotBlank(message = "Email is required")
    private String address;

    @NotBlank(message = "phone is required")
    private String phone;

}
