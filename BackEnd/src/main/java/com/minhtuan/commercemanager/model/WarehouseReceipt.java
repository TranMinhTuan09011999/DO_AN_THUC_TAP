package com.minhtuan.commercemanager.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="phieunhap")
@ToString
public class WarehouseReceipt {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MAPN", nullable = false)
    private String warehouseReceiptId;

    @Column(name = "NGAYNHAP", nullable = false)
    private Date dateOfIssue;

    @Column(name = "TONGTIEN", nullable = false)
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "MANV", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Employee employee;

    @OneToMany(mappedBy = "warehouseReceipt", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<WarehouseReceiptDetail> warehouseReceiptDetails;
}
