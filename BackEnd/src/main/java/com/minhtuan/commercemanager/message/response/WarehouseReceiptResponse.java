package com.minhtuan.commercemanager.message.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.minhtuan.commercemanager.model.WarehouseReceipt;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WarehouseReceiptResponse {
    private String warehouseReceiptId;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfIssue;
    private Double amount;
    private String firstname;
    private String lastname;
}
