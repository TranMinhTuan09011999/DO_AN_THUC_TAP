package com.minhtuan.commercemanager.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ColorDTO {
    private static final long serialVersionUID = 1L;

    private Integer colorId;
    private String colorCode;
    private String colorName;
}
