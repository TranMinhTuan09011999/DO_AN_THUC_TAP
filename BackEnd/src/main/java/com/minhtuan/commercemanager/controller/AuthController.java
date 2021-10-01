package com.minhtuan.commercemanager.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.minhtuan.commercemanager.exception.EmailAlreadyExistedException;
import com.minhtuan.commercemanager.message.request.LoginRequest;
import com.minhtuan.commercemanager.message.request.SignupRequest;
import com.minhtuan.commercemanager.message.response.JwtResponse;
import com.minhtuan.commercemanager.message.response.MessageResponse;
import com.minhtuan.commercemanager.model.*;
import com.minhtuan.commercemanager.repository.AccountRepository;
import com.minhtuan.commercemanager.repository.CustomerRepository;
import com.minhtuan.commercemanager.repository.EmployeeRepository;
import com.minhtuan.commercemanager.repository.RoleRepository;
import com.minhtuan.commercemanager.security.jwt.JwtUtils;
import com.minhtuan.commercemanager.security.services.UserDetailsImpl;
import com.minhtuan.commercemanager.services.CaptchaService;
import com.minhtuan.commercemanager.services.EmailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private CaptchaService captchaService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        String email = userDetails.getEmail();
        String role = roles.get(0);
        System.out.println(role);
        if(role.equals("ROLE_ADMIN") || role.equals("ROLE_EMPLOYEE"))
        {
            Employee employee = employeeRepository.findByEmail(email).get();
            JwtResponse jwtResponse = new JwtResponse();
            if(employee.getStatus() == 0){
                jwtResponse.setMessage("Account don't exist");
                jwtResponse.setStatus(400);
                return ResponseEntity.ok(jwtResponse);
            }
            boolean captchaVerified = captchaService.verify(loginRequest.getRecaptchaResponse());
            if(!captchaVerified) {
                jwtResponse.setMessage("Invalid captcha");
                jwtResponse.setStatus(400);
            }else {
                jwtResponse.setMessage("Success");
                jwtResponse.setStatus(200);
                jwtResponse.setToken(jwt);
                jwtResponse.setId(employee.getId());
                jwtResponse.setEmail(employee.getEmail());
                jwtResponse.setPhone(employee.getPhone());
                jwtResponse.setAddress(employee.getAddress());
                jwtResponse.setFirstname(employee.getFirstname());
                jwtResponse.setLastname(employee.getLastname());
                jwtResponse.setGender(employee.getGender());
                jwtResponse.setBirthday(employee.getBirthday());
                jwtResponse.setRole(role);
            }
            return ResponseEntity.ok(jwtResponse);
        }else {
            Customer customer = customerRepository.findByEmail(email).get();
            JwtResponse jwtResponse = new JwtResponse();
            if(customer.getStatus() == 0){
                jwtResponse.setMessage("Account don't exist");
                jwtResponse.setStatus(400);
                return ResponseEntity.ok(jwtResponse);
            }
            boolean captchaVerified = captchaService.verify(loginRequest.getRecaptchaResponse());
            if(!captchaVerified) {
                jwtResponse.setMessage("Invalid captcha");
                jwtResponse.setStatus(400);
            }else {
                jwtResponse.setMessage("Success");
                jwtResponse.setStatus(200);
                jwtResponse.setToken(jwt);
                jwtResponse.setId(customer.getId());
                jwtResponse.setEmail(customer.getEmail());
                jwtResponse.setPhone(customer.getPhone());
                jwtResponse.setAddress(customer.getAddress());
                jwtResponse.setFirstname(customer.getFirstname());
                jwtResponse.setLastname(customer.getLastname());
                jwtResponse.setGender(customer.getGender());
                jwtResponse.setBirthday(customer.getBirthday());
                jwtResponse.setRole(role);
            }
            return ResponseEntity.ok(jwtResponse);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (accountRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailAlreadyExistedException("Email has been already existed!!!");
        }
        // Create new user's account
        String role = signUpRequest.getRole();
        switch (role) {
            case "admin":
                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                Account account = new Account(signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()),null, adminRole);
                accountRepository.save(account);

                List<Employee> employeeList = employeeRepository.findAll();
                if(employeeList.size() > 0) {
                    Integer end = employeeList.size() - 1;
                    String id = employeeList.get(end).getId();
                    String IdInt = id.substring(2);
                    String IdBegin = id.substring(0,2);
                    Integer newIdInt = Integer.parseInt(IdInt);
                    newIdInt += 1;
                    String newIdString = newIdInt.toString();
                    if(newIdString.length() == 1)
                    {
                        newIdString = "0000" + newIdString;
                    }else if(newIdString.length() == 2)
                    {
                        newIdString = "000" + newIdString;
                    }else if(newIdString.length() == 3)
                    {
                        newIdString = "00" + newIdString;
                    }
                    else if(newIdString.length() == 4)
                    {
                        newIdString = "0" + newIdString;
                    }
                    String idNew = IdBegin + newIdString;
                    Employee employee = new Employee(idNew, signUpRequest.getFirstname(),signUpRequest.getLastname(), signUpRequest.getGender(), signUpRequest.getBirthday(), signUpRequest.getAddress(),signUpRequest.getPhone(), signUpRequest.getEmail(), 1);
                    System.out.println(employee.getId());
                    employeeRepository.save(employee);
                    return ResponseEntity.ok(new MessageResponse("Account admin registered successfully!"));
                }else {
                    Employee employee = new Employee("NV00001", signUpRequest.getFirstname(),signUpRequest.getLastname(), signUpRequest.getGender(), signUpRequest.getBirthday(), signUpRequest.getAddress(),signUpRequest.getPhone(), signUpRequest.getEmail(), 1);
                    System.out.println(employee.getId());
                    employeeRepository.save(employee);
                    return ResponseEntity.ok(new MessageResponse("Account admin registered successfully!"));
                }
            case "employee":
                Role employeeRole = roleRepository.findByName(ERole.ROLE_EMPLOYEE)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                Account employeeAccount = new Account(signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()),null, employeeRole);
                accountRepository.save(employeeAccount);
                List<Employee> employeeLists = employeeRepository.findAll();
                if(employeeLists.size() > 0) {
                    Integer end = employeeLists.size() - 1;
                    String id = employeeLists.get(end).getId();
                    String IdInt = id.substring(2);
                    String IdBegin = id.substring(0,2);
                    Integer newIdInt = Integer.parseInt(IdInt);
                    newIdInt += 1;
                    String newIdString = newIdInt.toString();
                    if(newIdString.length() == 1)
                    {
                        newIdString = "0000" + newIdString;
                    }else if(newIdString.length() == 2)
                    {
                        newIdString = "000" + newIdString;
                    }else if(newIdString.length() == 3)
                    {
                        newIdString = "00" + newIdString;
                    }
                    else if(newIdString.length() == 4)
                    {
                        newIdString = "0" + newIdString;
                    }
                    String idNew = IdBegin + newIdString;
                    Employee employee = new Employee(idNew, signUpRequest.getFirstname(),signUpRequest.getLastname(), signUpRequest.getGender(), signUpRequest.getBirthday(), signUpRequest.getAddress(),signUpRequest.getPhone(), signUpRequest.getEmail(), 1);
                    employeeRepository.save(employee);
                    return ResponseEntity.ok(new MessageResponse("Account employee registered successfully!"));
                }else {
                    Employee employee = new Employee("NV00001", signUpRequest.getFirstname(),signUpRequest.getLastname(), signUpRequest.getGender(), signUpRequest.getBirthday(), signUpRequest.getAddress(),signUpRequest.getPhone(), signUpRequest.getEmail(), 1);
                    employeeRepository.save(employee);
                    return ResponseEntity.ok(new MessageResponse("Account employee registered successfully!"));
                }
            default:
                Role customerRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                Account customerAccount = new Account(signUpRequest.getEmail(),encoder.encode(signUpRequest.getPassword()),null, customerRole);
                accountRepository.save(customerAccount);
                List<Customer> customerList = customerRepository.findAll();
                if(customerList.size() > 0) {
                    Integer end = customerList.size() - 1;
                    String id = customerList.get(end).getId();
                    String IdInt = id.substring(2);
                    String IdBegin = id.substring(0,2);
                    Integer newIdInt = Integer.parseInt(IdInt);
                    newIdInt += 1;
                    String newIdString = newIdInt.toString();
                    if(newIdString.length() == 1)
                    {
                        newIdString = "0000" + newIdString;
                    }else if(newIdString.length() == 2)
                    {
                        newIdString = "000" + newIdString;
                    }else if(newIdString.length() == 3)
                    {
                        newIdString = "00" + newIdString;
                    }
                    else if(newIdString.length() == 4)
                    {
                        newIdString = "0" + newIdString;
                    }
                    String idNew = IdBegin + newIdString;
                    Customer customer = new Customer(idNew, signUpRequest.getFirstname(),signUpRequest.getLastname(), signUpRequest.getGender(), signUpRequest.getBirthday(), signUpRequest.getAddress(),signUpRequest.getPhone(), signUpRequest.getEmail(), 1);
                    customerRepository.save(customer);
                    return ResponseEntity.ok(new MessageResponse("Account customer registered successfully!"));
                }else {
                    Customer customer = new Customer("KH00001", signUpRequest.getFirstname(),signUpRequest.getLastname(), signUpRequest.getGender(), signUpRequest.getBirthday(), signUpRequest.getAddress(),signUpRequest.getPhone(), signUpRequest.getEmail(), 1);
                    customerRepository.save(customer);
                    return ResponseEntity.ok(new MessageResponse("Account customer registered successfully!"));
                }
        }
    }

    @PostMapping("/forgot/{email}")
    public ResponseEntity<?> processForgotPassword(@PathVariable String email, HttpServletRequest request){
        Optional<Account> optional = accountRepository.findByEmail(email);
        if (!optional.isPresent()) {
            return ResponseEntity.ok(new MessageResponse("Email not exist"));
        } else {
            Account account = optional.get();
            account.setResetToken(UUID.randomUUID().toString());

            // Save token to database
            accountRepository.save(account);

            String appUrl = request.getScheme() + "://" + request.getServerName();

            // Email message
            SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
            passwordResetEmail.setFrom("tranminhtuannhj@gmail.com");
            passwordResetEmail.setTo(account.getEmail());
            passwordResetEmail.setSubject("Password Reset Request");
            passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
                    + ":4200/reset?token=" + account.getResetToken());
            emailService.sendEmail(passwordResetEmail);
            return ResponseEntity.ok(new MessageResponse("Send mail successfully!"));
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<?> setNewPassword(@RequestParam Map<String, String> requestParams){
        Optional<Account> account = accountRepository.findByResetToken(requestParams.get("token"));
        if (account.isPresent()) {
            Account resetAccount = account.get();
            resetAccount.setPassword(encoder.encode(requestParams.get("password")));
            resetAccount.setResetToken(null);
            accountRepository.save(resetAccount);
            return ResponseEntity.ok(new MessageResponse("Change password successfully!"));
        } else {
            return ResponseEntity.ok(new MessageResponse("User not exist"));
        }
    }
}
