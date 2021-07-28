package com.minhtuan.commercemanager.controller.admin;

import com.minhtuan.commercemanager.dto.CategoryDTO;
import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.message.request.CategoryRequest;
import com.minhtuan.commercemanager.message.request.ProductRequest;
import com.minhtuan.commercemanager.services.CategoryService;
import com.minhtuan.commercemanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class ProductAdminController {

    @Autowired
    ProductService productService;

    @PostMapping("/add-product")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
        ProductDTO productDTO = productService.save(productRequest);
        return ResponseEntity.ok().body(productDTO);
    }

    @GetMapping("/product")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> getProduct() {
        List<ProductDTO> productDTOList = productService.getAllProduct();
        return ResponseEntity.ok().body(productDTOList);
    }
}
