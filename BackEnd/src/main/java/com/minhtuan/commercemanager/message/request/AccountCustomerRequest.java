package com.minhtuan.commercemanager.message.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AccountCustomerRequest {
    private String firstname;
    private String lastname;
    private Integer gender;
    private String phone;
    private Date birthday;
    private String address;
}
