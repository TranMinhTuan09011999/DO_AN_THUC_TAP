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
@Table(name="hoadon")
@ToString
public class Invoice {
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "invoiceId is required")
    @Column(name = "MAHD", nullable = false)
    private String invoiceId;

    @Column(name = "NGAY", nullable = false)
    private Date date;

    @Column(name = "THANHTIEN", nullable = false)
    private Double amount;

    @NotBlank(message = "taxId is required")
    @Column(name = "MASOTHUE", nullable = false)
    private String taxId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MAPD", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Order orderInvoice;
}
