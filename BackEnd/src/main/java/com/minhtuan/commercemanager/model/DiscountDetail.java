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
@Table(name="ct_khuyenmai")
@ToString
public class DiscountDetail {
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "discountDetailId is required")
    @Column(name = "MACTKM", nullable = false)
    private String discountDetailId;

    @NotBlank(message = "percent is required")
    @Column(name = "PHANTRAM", nullable = false)
    private Integer percent;
}
