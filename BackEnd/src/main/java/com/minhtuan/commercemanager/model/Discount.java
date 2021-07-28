package com.minhtuan.commercemanager.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="khuyenmai")
@ToString
public class Discount {
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "discountId is required")
    @Column(name = "MAKM", nullable = false)
    private String discountId;

    @NotBlank(message = "discountName is required")
    @Column(name = "TENKM", nullable = false)
    private String discountName;

    @NotBlank(message = "dateOfBegin is required")
    @Column(name = "NGAYBATDAU", nullable = false)
    private Date dateOfBegin;

    @NotBlank(message = "dateOfEnd is required")
    @Column(name = "NGAYKETTHUC", nullable = false)
    private Date dateOfEnd;

    @NotBlank(message = "description is required")
    @Column(name = "MOTA", nullable = false)
    private String description;
}
