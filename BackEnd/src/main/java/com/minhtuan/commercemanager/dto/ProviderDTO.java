package com.minhtuan.commercemanager.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProviderDTO {
    private static final long serialVersionUID = 1L;

    private String providerId;
    private String providerName;
    private String providerAddress;
    private String providerEmail;
    private String providerPhone;
    private Integer status;
}
