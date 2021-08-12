package com.minhtuan.commercemanager.message.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class ColorByProductIdAndSizeIdResponse {

    private Integer colorId;
    private String colorCode;

    public ColorByProductIdAndSizeIdResponse(Object[] objects){
        this.colorId = objects[0] != null ? Integer.valueOf(objects[0].toString()) : null;
        this.colorCode = objects[1] != null ? String.valueOf(objects[1]) : null;
    }
}
