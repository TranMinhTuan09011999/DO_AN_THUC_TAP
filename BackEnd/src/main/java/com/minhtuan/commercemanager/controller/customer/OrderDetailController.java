package com.minhtuan.commercemanager.controller.customer;

import com.minhtuan.commercemanager.message.request.OrderDetailRequest;
import com.minhtuan.commercemanager.message.request.OrderRequest;
import com.minhtuan.commercemanager.repository.OrderDetailRepository;
import com.minhtuan.commercemanager.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping("/orderDetail")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> saveOrderProduct(@RequestBody OrderDetailRequest orderDetailRequest){
        orderDetailService.save(orderDetailRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}