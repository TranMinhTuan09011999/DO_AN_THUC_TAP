package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.CustomerDTO;
import com.minhtuan.commercemanager.dto.EmployeeDTO;
import com.minhtuan.commercemanager.message.request.AccountCustomerRequest;
import com.minhtuan.commercemanager.message.response.OrderResponse;

import java.util.List;

public interface CustomerService {
    public List<CustomerDTO> getAllCustomer();
    public CustomerDTO getCustomerByID(String customerId);
    public CustomerDTO updateAccountCustomer(String customerId,AccountCustomerRequest accountCustomerRequest);
    public void deleteCustomer(String customerId, Integer status);
    public Long customerCount();
}
