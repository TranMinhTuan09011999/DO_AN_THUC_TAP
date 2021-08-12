package com.minhtuan.commercemanager.controller.customer;

import com.minhtuan.commercemanager.dto.ProductConditionDTO;
import com.minhtuan.commercemanager.message.request.OrderRequest;
import com.minhtuan.commercemanager.message.response.OrderResponse;
import com.minhtuan.commercemanager.services.OrderService;
import com.minhtuan.commercemanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/order")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> orderProduct(@RequestBody OrderRequest orderRequest){
        String orderId = orderService.save(orderRequest);
        return ResponseEntity.ok(orderId);
    }

    @GetMapping("/order/all")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getOrderList(){
        List<OrderResponse> orderResponseList = orderService.getAllOrder();
        //Nên dùng return như này
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseList);
    }
}
