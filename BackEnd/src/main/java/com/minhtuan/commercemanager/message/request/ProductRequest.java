package com.minhtuan.commercemanager.message.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductRequest {

    private static final long serialVersionUID = 1L;

    private String productName;
    private Integer status;
    private String description;
    private String providerId;
    private String categoryId;
}
