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
@Table(name="hoadon")
@ToString
public class Invoice {
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "invoiceId is required")
    @Column(name = "MAHD", nullable = false)
    private String invoiceId;

    @NotBlank(message = "date is required")
    @Column(name = "NGAY", nullable = false)
    private Date date;

    @NotBlank(message = "amount is required")
    @Column(name = "THANHTIEN", nullable = false)
    private Double amount;

    @NotBlank(message = "taxId is required")
    @Column(name = "MASOTHUE", nullable = false)
    private String taxId;
}
