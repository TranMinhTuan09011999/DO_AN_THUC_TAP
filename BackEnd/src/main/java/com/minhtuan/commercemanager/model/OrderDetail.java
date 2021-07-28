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

    @NotBlank(message = "price is required")
    @Column(name = "GIA", nullable = false)
    private Double price;

    @NotBlank(message = "quantity is required")
    @Column(name = "SOLUONG", nullable = false)
    private Integer quantity;
}
