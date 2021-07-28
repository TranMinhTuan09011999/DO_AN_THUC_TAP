package com.minhtuan.commercemanager.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDTO {
    private static final long serialVersionUID = 1L;

    private String id;
    private String firstname;
    private String lastname;
    private Integer gender;
    private String birthday;
    private String address;
    private String phone;
    private String email;
}
