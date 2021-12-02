package com.minhtuan.commercemanager.controller.customer;

import com.minhtuan.commercemanager.message.response.ColorByProductIdAndSizeIdResponse;
import com.minhtuan.commercemanager.message.response.SizeByProductIdResponse;
import com.minhtuan.commercemanager.services.ColorService;
import com.minhtuan.commercemanager.services.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/customer")
public class SizeCustomerController {

    @Autowired
    SizeService sizeService;

    @GetMapping("/size/{productId}/{sizeId}")
    public ResponseEntity<?> getSizeByProductId(@PathVariable(value = "productId") String productId, @PathVariable(value = "sizeId") Integer sizeId) {
        List<SizeByProductIdResponse> sizeByProductIdResponseList = sizeService.getSizeByProductId(productId, sizeId);
        return ResponseEntity.ok().body(sizeByProductIdResponseList);
    }
}
