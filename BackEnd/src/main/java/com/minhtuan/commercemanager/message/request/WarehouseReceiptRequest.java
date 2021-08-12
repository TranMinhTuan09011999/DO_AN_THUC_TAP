package com.minhtuan.commercemanager.message.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WarehouseReceiptRequest {
    private String employeeId;
    private Double amount;
}
