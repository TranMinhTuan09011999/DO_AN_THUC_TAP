package com.minhtuan.commercemanager.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseReceiptDetailId implements Serializable {

    private String warehouseReceipt;
    private Integer productDetail;
}
