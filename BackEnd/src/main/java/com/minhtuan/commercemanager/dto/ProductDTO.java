package com.minhtuan.commercemanager.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO {

    private static final long serialVersionUID = 1L;

    private String productId;
    private String productName;
    private Integer status;
    private String description;
    private ProviderDTO providerDTO;
    private CategoryDTO categoryDTO;
}
