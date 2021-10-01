package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.dto.CustomerDTO;
import com.minhtuan.commercemanager.dto.EmployeeDTO;
import com.minhtuan.commercemanager.model.Customer;
import com.minhtuan.commercemanager.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDTO toDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setFirstname(customer.getFirstname());
        customerDTO.setLastname(customer.getLastname());
        customerDTO.setGender(customer.getGender());
        customerDTO.setBirthday(customer.getBirthday());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setStatus(customer.getStatus());
        return customerDTO;
    }
}
