package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.CustomerDTO;
import com.minhtuan.commercemanager.dto.EmployeeDTO;
import com.minhtuan.commercemanager.maper.CustomerMapper;
import com.minhtuan.commercemanager.maper.EmployeeMapper;
import com.minhtuan.commercemanager.message.request.AccountCustomerRequest;
import com.minhtuan.commercemanager.message.response.OrderResponse;
import com.minhtuan.commercemanager.model.Customer;
import com.minhtuan.commercemanager.model.Employee;
import com.minhtuan.commercemanager.model.Order;
import com.minhtuan.commercemanager.repository.CustomerRepository;
import com.minhtuan.commercemanager.repository.EmployeeRepository;
import com.minhtuan.commercemanager.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> customerList = customerRepository.findAllByStatus(1);
        List<CustomerDTO> customerDTOList = customerList.stream().map(customer -> customerMapper.toDTO(customer)).collect(Collectors.toList());
        return customerDTOList;
    }

    @Override
    public CustomerDTO getCustomerByID(String customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        CustomerDTO customerDTO = customerMapper.toDTO(customer);
        return customerDTO;
    }

    @Override
    public CustomerDTO updateAccountCustomer(String customerId, AccountCustomerRequest accountCustomerRequest) {
        Customer customer = customerRepository.findCustomerById(customerId);
        customer.setFirstname(accountCustomerRequest.getFirstname());
        customer.setLastname(accountCustomerRequest.getLastname());
        customer.setGender(accountCustomerRequest.getGender());
        customer.setPhone(accountCustomerRequest.getPhone());
        customer.setBirthday(accountCustomerRequest.getBirthday());
        customer.setAddress(accountCustomerRequest.getAddress());
        customerRepository.save(customer);
        CustomerDTO customerDTO = customerMapper.toDTO(customer);
        return customerDTO;
    }

    @Override
    public void deleteCustomer(String customerId, Integer status) {
        Customer customer = customerRepository.getById(customerId);
        customer.setStatus(status);
        customerRepository.save(customer);
    }

    @Override
    public Long customerCount() {
        Long count = customerRepository.countAllByStatus(1);
        return count;
    }
}
