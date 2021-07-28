package com.minhtuan.commercemanager.message.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(value= JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ProductDetailRequest {

    private static final long serialVersionUID = 1L;

    private Integer quantity;
    private Double price;

    @JsonProperty("image")
    private String image;
    private String productId;
    private Integer colorId;
    private Integer sizeId;
}
