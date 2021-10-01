package com.minhtuan.commercemanager.controller.admin;

import com.minhtuan.commercemanager.dto.CustomerDTO;
import com.minhtuan.commercemanager.dto.EmployeeDTO;
import com.minhtuan.commercemanager.message.request.AccountCustomerRequest;
import com.minhtuan.commercemanager.message.response.ApiResponse;
import com.minhtuan.commercemanager.message.response.JwtResponse;
import com.minhtuan.commercemanager.message.response.OrderResponse;
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
public class EmployeeAdminController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employee/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getAllEmployee(){
        List<EmployeeDTO> employeeDTOList = employeeService.getAllEmployee();
        //Nên dùng return như này
        return ResponseEntity.status(HttpStatus.OK).body(employeeDTOList);
    }

    @GetMapping("/employee/{employeeId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getEmployeeById(@PathVariable(value = "employeeId") String employeeId){
        EmployeeDTO employeeDTO = employeeService.getEmployeeByID(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(employeeDTO);
    }

    @PutMapping("account/update/{employeeId}/{role}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> updateAccountEmployee(@PathVariable(value = "employeeId") String employeeId, @PathVariable(value = "role") String role, @RequestBody AccountCustomerRequest accountCustomerRequest){
        EmployeeDTO employeeDTO = employeeService.updateAccountEmployee(employeeId,accountCustomerRequest);
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setId(employeeDTO.getId());
        jwtResponse.setEmail(employeeDTO.getEmail());
        jwtResponse.setPhone(employeeDTO.getPhone());
        jwtResponse.setAddress(employeeDTO.getAddress());
        jwtResponse.setFirstname(employeeDTO.getFirstname());
        jwtResponse.setLastname(employeeDTO.getLastname());
        jwtResponse.setGender(employeeDTO.getGender());
        jwtResponse.setBirthday(employeeDTO.getBirthday());
        jwtResponse.setRole(role);
        return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
    }

    @PutMapping("/employee/status/{employeeId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "employeeId") String employeeId, @RequestBody HashMap<String, Integer> status){
        try {
            Integer status1 = status.get("status");
            employeeService.deleteEmployee(employeeId,status1);
            return ResponseEntity.status(HttpStatus.OK).body("Employee already deleted");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }

    @GetMapping("/employee/count")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getEmployeeCount(){
        Long count = employeeService.employeeCount();
        //Nên dùng return như này
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }
}
