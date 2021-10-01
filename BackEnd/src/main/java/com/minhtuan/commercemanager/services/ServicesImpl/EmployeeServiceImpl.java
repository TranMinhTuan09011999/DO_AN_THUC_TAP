package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.CustomerDTO;
import com.minhtuan.commercemanager.dto.EmployeeDTO;
import com.minhtuan.commercemanager.dto.OrderDetailDTO;
import com.minhtuan.commercemanager.maper.EmployeeMapper;
import com.minhtuan.commercemanager.message.request.AccountCustomerRequest;
import com.minhtuan.commercemanager.model.Customer;
import com.minhtuan.commercemanager.model.Employee;
import com.minhtuan.commercemanager.repository.EmployeeRepository;
import com.minhtuan.commercemanager.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDTO> getAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAllByStatus(1);
        List<EmployeeDTO> employeeDTOList = employeeList.stream().map(employee -> employeeMapper.toDTO(employee)).collect(Collectors.toList());
        return employeeDTOList;
    }

    @Override
    public EmployeeDTO getEmployeeByID(String employeeId) {
        Employee employee = employeeRepository.getById(employeeId);
        EmployeeDTO employeeDTO = employeeMapper.toDTO(employee);
        return employeeDTO;
    }

    @Override
    public EmployeeDTO updateAccountEmployee(String employeeId, AccountCustomerRequest accountCustomerRequest) {
        Employee employee = employeeRepository.getById(employeeId);
        employee.setFirstname(accountCustomerRequest.getFirstname());
        employee.setLastname(accountCustomerRequest.getLastname());
        employee.setGender(accountCustomerRequest.getGender());
        employee.setPhone(accountCustomerRequest.getPhone());
        employee.setBirthday(accountCustomerRequest.getBirthday());
        employee.setAddress(accountCustomerRequest.getAddress());
        employeeRepository.save(employee);
        EmployeeDTO employeeDTO = employeeMapper.toDTO(employee);
        return employeeDTO;
    }

    @Override
    public void deleteEmployee(String employeeId, Integer status) {
        Employee employee = employeeRepository.getById(employeeId);
        employee.setStatus(status);
        employeeRepository.save(employee);
    }

    @Override
    public Long employeeCount() {
        Long count = employeeRepository.countAllByStatus(1);
        return count;
    }
}
