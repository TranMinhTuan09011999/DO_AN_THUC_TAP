package com.minhtuan.commercemanager.controller.admin;

import com.minhtuan.commercemanager.dto.CategoryDTO;
import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.message.request.CategoryRequest;
import com.minhtuan.commercemanager.message.request.ProductRequest;
import com.minhtuan.commercemanager.services.CategoryService;
import com.minhtuan.commercemanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
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
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
        ProductDTO productDTO = productService.save(productRequest);
        return ResponseEntity.ok().body(productDTO);
    }

    @GetMapping("/product")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getProduct() {
        List<ProductDTO> productDTOList = productService.getAllProduct();
        return ResponseEntity.ok().body(productDTOList);
    }

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getProductByProductId(@PathVariable(value = "productId") String productId) {
        ProductDTO productDTO = productService.getProductByProductId(productId);
        return ResponseEntity.ok().body(productDTO);
    }

    @PutMapping("/product/update/{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> updateProductByProductId(@PathVariable(value = "productId") String productId, @RequestBody ProductRequest productRequest) {
        ProductDTO productDTO = productService.updateProductByProductId(productId, productRequest);
        return ResponseEntity.ok().body(productDTO);
    }

    @GetMapping("/product/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getProductByProductId(@Param(value = "productId") String productId,
                                                   @Param(value = "productName") String productName,
                                                   @Param(value = "categoryId") String categoryId,
                                                    @Param(value = "providerId") String providerId,
                                                    @Param(value = "status") Integer status) {
        List<ProductDTO> productDTOList = productService.getAllProductBySearch(productId,productName,categoryId,providerId, status);
        return ResponseEntity.ok().body(productDTOList);
    }

    @GetMapping("/product/count")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getNewProductCount(){
        Long count = productService.newProductCount();
        //Nên dùng return như này
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }
}
