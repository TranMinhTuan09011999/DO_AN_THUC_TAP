package com.minhtuan.commercemanager.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class WarehouseReceiptDTO {
    private String warehouseReceiptId;
    private Date dateOfIssue;
    private Double amount;
    private String firstname;
    private String lastname;
}
