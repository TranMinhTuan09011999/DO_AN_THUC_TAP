package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.dto.EmployeeDTO;
import com.minhtuan.commercemanager.message.response.WarehouseReceiptResponse;
import com.minhtuan.commercemanager.model.Employee;
import com.minhtuan.commercemanager.model.WarehouseReceipt;
import com.minhtuan.commercemanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WarehouseReceiptMapper {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public WarehouseReceiptResponse toWarehouseReceiptResponse(WarehouseReceipt warehouseReceipt){
        WarehouseReceiptResponse warehouseReceiptResponse = new WarehouseReceiptResponse();
        warehouseReceiptResponse.setWarehouseReceiptId(warehouseReceipt.getWarehouseReceiptId());
        warehouseReceiptResponse.setDateOfIssue(warehouseReceipt.getDateOfIssue());
        warehouseReceiptResponse.setAmount(warehouseReceipt.getAmount());
        EmployeeDTO employeeDTO = employeeMapper.toDTO(warehouseReceipt.getEmployee());
        warehouseReceiptResponse.setEmployee(employeeDTO);
        return warehouseReceiptResponse;
    }
}
