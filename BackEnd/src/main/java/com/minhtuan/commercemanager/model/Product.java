package com.minhtuan.commercemanager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="sanpham")
@ToString
public class Product {
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "productId is required")
    @Column(name = "MASP", nullable = false)
    private String productId;

    @NotBlank(message = "productName is required")
    @Column(name = "TENSP", nullable = false)
    private String productName;

    @Column(name = "TRANGTHAI", nullable = false)
    private Integer status;

    @NotBlank(message = "description is required")
    @Column(name = "MOTA", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "MANCC")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Provider provider;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MADMH")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;


}
