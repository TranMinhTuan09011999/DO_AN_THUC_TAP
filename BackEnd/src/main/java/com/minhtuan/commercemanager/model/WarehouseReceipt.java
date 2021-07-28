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
@Table(name="phieunhap")
@ToString
public class WarehouseReceipt {
    private static final long serialVersionUID = 1L;

    @Id
    @NotBlank(message = "warehouseReceiptIid is required")
    @Column(name = "MAPN", nullable = false)
    private Integer warehouseReceiptIid;

    @NotBlank(message = "dateOfIssue is required")
    @Column(name = "NGAYNHAP", nullable = false)
    private Date dateOfIssue;
}
