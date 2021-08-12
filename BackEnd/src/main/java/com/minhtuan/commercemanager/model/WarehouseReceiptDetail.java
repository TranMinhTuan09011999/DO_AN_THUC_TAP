package com.minhtuan.commercemanager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="ct_phieunhap")
@ToString
@IdClass(WarehouseReceiptDetailId.class)
public class WarehouseReceiptDetail {
    private static final long serialVersionUID = 1L;

    @Column(name = "SOLUONG", nullable = false)
    private Integer quantity;

    @Column(name = "GIA", nullable = false)
    private Double price;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAPN", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private WarehouseReceipt warehouseReceipt;

    @Id
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MACTSP", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductDetail productDetail;
}
