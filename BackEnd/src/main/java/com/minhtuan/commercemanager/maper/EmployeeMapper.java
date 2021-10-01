package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.dto.EmployeeDTO;
import com.minhtuan.commercemanager.dto.OrderDetailDTO;
import com.minhtuan.commercemanager.model.Employee;
import com.minhtuan.commercemanager.model.OrderDetail;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public EmployeeDTO toDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstname(employee.getFirstname());
        employeeDTO.setLastname(employee.getLastname());
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setBirthday(employee.getBirthday());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setPhone(employee.getPhone());
        employeeDTO.setEmail(employee.getEmail());
        return employeeDTO;
    }
}
