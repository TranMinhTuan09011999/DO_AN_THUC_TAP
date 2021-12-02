package com.minhtuan.commercemanager.controller.customer;

import com.minhtuan.commercemanager.dto.OrderDTO;
import com.minhtuan.commercemanager.dto.ProductConditionDTO;
import com.minhtuan.commercemanager.message.request.OrderDetailRequest;
import com.minhtuan.commercemanager.message.request.OrderRequest;
import com.minhtuan.commercemanager.message.request.ReceiptRequest;
import com.minhtuan.commercemanager.message.response.ApiResponse;
import com.minhtuan.commercemanager.message.response.OrderResponse;
import com.minhtuan.commercemanager.services.OrderService;
import com.minhtuan.commercemanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("http://localhost:4200")
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

    @GetMapping("/order/all/{customerId}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getOrderList(@PathVariable(value = "customerId") String customerId){
        List<OrderResponse> orderResponseList = orderService.getAllOrderByCustomerId(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseList);
    }

    @PutMapping("/order/status/{orderId}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<?> cancel(@PathVariable(value = "orderId") String orderId, @RequestBody HashMap<String, Integer> status){
        try {
            Integer status1 = status.get("status");
            OrderResponse orderResponse = orderService.updateStatusOrder(orderId, status1);
            return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }

    @PutMapping("/order/payment/{orderId}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<?> payment(@PathVariable(value = "orderId") String orderId, @RequestBody HashMap<String, Integer> status){
        try {
            Integer payment = status.get("payment");
            OrderResponse orderResponse = orderService.updatePaymentOrder(orderId, payment);
            return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }

    @GetMapping("/order/all/{customer}/{status}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getOrderList(@PathVariable(value = "customer") String customer, @PathVariable(value = "status") Integer status){
        List<OrderResponse> orderResponseList = orderService.getAllOrderByCustomerId(customer, status);
        //Nên dùng return như này
        return ResponseEntity.status(HttpStatus.OK).body(orderResponseList);
    }

    @GetMapping("/order/delivery/{customer}/{status}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getDeliveryOrderList(@PathVariable(value = "customer") String customer, @PathVariable(value = "status") Integer status){
        List<OrderDTO> orderDTOList = orderService.getAllDeliveryOrderByCustomer(customer, status);
        //Nên dùng return như này
        return ResponseEntity.status(HttpStatus.OK).body(orderDTOList);
    }

}
