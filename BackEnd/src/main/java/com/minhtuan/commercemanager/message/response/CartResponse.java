package com.minhtuan.commercemanager.message.response;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CartResponse implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer productDetailId;
    private String productId;
    private String productName;
    private Double price;
    private Integer sizedId;
    private String size;
    private Integer colorId;
    private String colorName;
    private Integer percent;

    public CartResponse(Object[] objects){
        this.productDetailId = objects[0] != null ? Integer.valueOf(objects[0].toString()) : null;
        this.productId = objects[1] != null ? String.valueOf(objects[1]) : null;
        this.productName = objects[2] != null ? String.valueOf(objects[2]) : null;
        this.price = objects[3] != null ? Double.valueOf(objects[3].toString()) : null;
        this.sizedId = objects[4] != null ? Integer.valueOf(objects[4].toString()) : null;
        this.size = objects[5] != null ? String.valueOf(objects[5]) : null;
        this.colorId = objects[6] != null ? Integer.valueOf(objects[6].toString()) : null;
        this.colorName = objects[7] != null ? String.valueOf(objects[7]) : null;
        this.percent = objects[8] != null ? Integer.valueOf(objects[8].toString()) : null;
    }


}
