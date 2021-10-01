package com.minhtuan.commercemanager.controller.customer;

import com.minhtuan.commercemanager.dto.OrderDetailDTO;
import com.minhtuan.commercemanager.message.request.OrderDetailRequest;
import com.minhtuan.commercemanager.message.request.OrderRequest;
import com.minhtuan.commercemanager.message.response.ApiResponse;
import com.minhtuan.commercemanager.message.response.OrderResponse;
import com.minhtuan.commercemanager.repository.OrderDetailRepository;
import com.minhtuan.commercemanager.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping("/orderDetail")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> saveOrderProduct(@RequestBody OrderDetailRequest orderDetailRequest){
        System.out.println("aaaaaaaaaa");
        orderDetailService.save(orderDetailRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/orderDetail/{orderId}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getOrderDetailByOrderId(@PathVariable(value = "orderId") String orderId){
        try {
            List<OrderDetailDTO> orderDetailDTOList = orderDetailService.getOrderDetailsByOrderId(orderId);
            return ResponseEntity.status(HttpStatus.OK).body(orderDetailDTOList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }
}
