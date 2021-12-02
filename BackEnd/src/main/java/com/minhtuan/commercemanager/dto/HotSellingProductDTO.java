package com.minhtuan.commercemanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HotSellingProductDTO {
    private String name;
    private Long value;
    private String text;

    public HotSellingProductDTO(Object[] objects){
        this.name = objects[0] != null ? String.valueOf(objects[0]) : null;
        this.value = objects[1] != null ? Long.valueOf(objects[1].toString()) : null;
        this.text = objects[1] != null ? String.valueOf(objects[1]) : null;
    }
}
