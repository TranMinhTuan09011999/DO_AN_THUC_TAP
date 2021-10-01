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
@Table(name="ct_phieudat")
@ToString
public class OrderDetail {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MACTPD", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderDetailId;

    @Column(name = "GIA", nullable = false)
    private Double price;

    @Column(name = "SOLUONG", nullable = false)
    private Integer quantity;

    @Column(name = "KHUYENMAI", nullable = false)
    private Integer discount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAPD", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MACTSP", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductDetail productDetail;
}
