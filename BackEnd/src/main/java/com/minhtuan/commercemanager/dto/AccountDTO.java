package com.minhtuan.commercemanager.dto;

import lombok.*;

@Getter
@Setter
@ToString
public class AccountDTO {
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
}
