package com.minhtuan.commercemanager.controller.admin;

import com.minhtuan.commercemanager.dto.CustomerDTO;
import com.minhtuan.commercemanager.dto.EmployeeDTO;
import com.minhtuan.commercemanager.message.response.ApiResponse;
import com.minhtuan.commercemanager.message.response.OrderResponse;
import com.minhtuan.commercemanager.services.CustomerService;
import com.minhtuan.commercemanager.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class CustomerAdminController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getAllCustomer(){
        List<CustomerDTO> customerDTOList = customerService.getAllCustomer();
        //Nên dùng return như này
        return ResponseEntity.status(HttpStatus.OK).body(customerDTOList);
    }

    @PutMapping("/customer/status/{customerId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> deleteCustomer(@PathVariable(value = "customerId") String customerId, @RequestBody HashMap<String, Integer> status){
        try {
            Integer status1 = status.get("status");
            customerService.deleteCustomer(customerId,status1);
            return ResponseEntity.status(HttpStatus.OK).body("Customer already deleted");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }

    @GetMapping("/customer/count")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getCustomerCount(){
        Long count = customerService.customerCount();
        //Nên dùng return như này
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }

}
