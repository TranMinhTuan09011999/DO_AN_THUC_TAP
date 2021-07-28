package com.minhtuan.commercemanager.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="ct_phieunhap")
@ToString
public class WarehouseReceiptDetail {
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "warehouseReceiptDetailId is required")
    @Column(name = "MACTPN", nullable = false)
    private Integer warehouseReceiptDetailId;

    @NotBlank(message = "quantity is required")
    @Column(name = "SOLUONG", nullable = false)
    private Integer quantity;

    @NotBlank(message = "price is required")
    @Column(name = "GIA", nullable = false)
    private Double price;

    @NotBlank(message = "size is required")
    @Column(name = "KICHTHUOC", nullable = false)
    private Integer size;

    @NotBlank(message = "color is required")
    @Column(name = "MAU", nullable = false)
    private Integer color;
}
