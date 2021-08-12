package com.minhtuan.commercemanager.controller.customer;

import com.minhtuan.commercemanager.dto.ProductConditionDTO;
import com.minhtuan.commercemanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class ProductCustomerController {

    @Autowired
    ProductService productService;

    @GetMapping("/{roomId}/{categoryId}")
    public ResponseEntity<?> getAllProductWithRoomIdAndCategoryId(@PathVariable(value = "roomId") Integer roomId, @PathVariable(value = "categoryId") String categoryId){
        System.out.println("aaa");
        List<ProductConditionDTO> productConditionDTOList = productService.getProductWithCondition(categoryId, roomId);
        return ResponseEntity.ok().body(productConditionDTOList);
    }
}
