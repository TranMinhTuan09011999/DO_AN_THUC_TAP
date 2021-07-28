package com.minhtuan.commercemanager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="ct_sanpham")
@ToString
public class ProductDetail {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MACTSP", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productDetailId;

    @Column(name = "SOLUONG", nullable = false)
    private Integer quantity;

    @Column(name = "GIA", nullable = false)
    private Double price;

    @Column(name = "HINHANH")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MASP", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAMAU", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAKT", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Size size;
}
