package com.minhtuan.commercemanager.message.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProviderRequest {
    private static final long serialVersionUID = 1L;

    private String providerName;
    private String providerAddress;
    private String providerEmail;
    private String providerPhone;
}
