package com.minhtuan.commercemanager.controller.customer;

import com.minhtuan.commercemanager.dto.CustomerDTO;
import com.minhtuan.commercemanager.message.request.AccountCustomerRequest;
import com.minhtuan.commercemanager.message.response.JwtResponse;
import com.minhtuan.commercemanager.message.response.OrderResponse;
import com.minhtuan.commercemanager.services.CustomerService;
import com.minhtuan.commercemanager.validation.email.EmailExistedValidator;
import com.minhtuan.commercemanager.validation.email.EmailNotExistedValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private EmailExistedValidator emailExistedValidator;

    @Autowired
    private EmailNotExistedValidator emailNotExistedValidator;

    @Autowired
    private CustomerService customerService;

    @GetMapping("/account/{customerId}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getCustomer(@PathVariable(value = "customerId") String customerId){
        CustomerDTO customerDTO = customerService.getCustomerByID(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @PutMapping("/account/update/{customerId}/{role}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> updateAccountCustomer(@PathVariable(value = "customerId") String customerId, @PathVariable(value = "role") String role, @RequestBody AccountCustomerRequest accountCustomerRequest){
        CustomerDTO customerDTO = customerService.updateAccountCustomer(customerId,accountCustomerRequest);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setId(customerDTO.getId());
        jwtResponse.setEmail(customerDTO.getEmail());
        jwtResponse.setPhone(customerDTO.getPhone());
        jwtResponse.setAddress(customerDTO.getAddress());
        jwtResponse.setFirstname(customerDTO.getFirstname());
        jwtResponse.setLastname(customerDTO.getLastname());
        jwtResponse.setGender(customerDTO.getGender());
        jwtResponse.setBirthday(customerDTO.getBirthday());
        jwtResponse.setRole(role);
        return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
    }

    @PostMapping("/customer/emailcheck")
    public ResponseEntity<?> emailCheck(@RequestBody Map<String, Object> inputData) {
        String email = (String)inputData.get("email");
        Boolean bool = emailExistedValidator.emailExists(email);
        return ResponseEntity.status(HttpStatus.OK).body(bool);
    }

    @PostMapping("/customer/emailcheckNotExist")
    public ResponseEntity<?> emailCheckNotExist(@RequestBody Map<String, Object> inputData) {
        String email = (String)inputData.get("email");
        Boolean bool = emailNotExistedValidator.emailExists(email);
        return ResponseEntity.status(HttpStatus.OK).body(bool);
    }
}
