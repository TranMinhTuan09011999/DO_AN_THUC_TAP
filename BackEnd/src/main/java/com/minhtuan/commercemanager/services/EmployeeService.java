package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.CustomerDTO;
import com.minhtuan.commercemanager.dto.EmployeeDTO;
import com.minhtuan.commercemanager.message.request.AccountCustomerRequest;

import java.util.List;

public interface EmployeeService {
    public List<EmployeeDTO> getAllEmployee();
    public EmployeeDTO getEmployeeByID(String employeeId);
    public EmployeeDTO updateAccountEmployee(String employeeId, AccountCustomerRequest accountCustomerRequest);
    public void deleteEmployee(String employeeId, Integer status);
    public Long employeeCount();
}
