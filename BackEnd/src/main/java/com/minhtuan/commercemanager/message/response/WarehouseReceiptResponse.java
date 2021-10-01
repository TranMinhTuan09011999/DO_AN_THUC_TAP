package com.minhtuan.commercemanager.message.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.minhtuan.commercemanager.dto.CustomerDTO;
import com.minhtuan.commercemanager.dto.EmployeeDTO;
import com.minhtuan.commercemanager.model.WarehouseReceipt;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WarehouseReceiptResponse {
    private String warehouseReceiptId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Ho_Chi_Minh")
    private Date dateOfIssue;
    private Double amount;
    EmployeeDTO employee;
}
