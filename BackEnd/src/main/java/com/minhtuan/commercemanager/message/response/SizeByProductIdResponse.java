package com.minhtuan.commercemanager.message.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class SizeByProductIdResponse {
    private Integer sizeId;
    private String sizeName;
    private Integer colorId;

    public SizeByProductIdResponse(Object[] objects){
        this.sizeId = objects[0] != null ? Integer.valueOf(objects[0].toString()) : null;
        this.sizeName = objects[1] != null ? String.valueOf(objects[1]) : null;
        this.colorId = objects[2] != null ? Integer.valueOf(objects[2].toString()) : null;
    }
}
