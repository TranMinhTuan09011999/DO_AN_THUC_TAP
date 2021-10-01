package com.minhtuan.commercemanager.controller.admin;

import com.minhtuan.commercemanager.message.request.WarehouseReceiptDetailRequest;
import com.minhtuan.commercemanager.message.request.WarehouseReceiptRequest;
import com.minhtuan.commercemanager.message.response.ColorByProductIdAndSizeIdResponse;
import com.minhtuan.commercemanager.message.response.WarehouseReceiptDetailResponse;
import com.minhtuan.commercemanager.message.response.WarehouseReceiptResponse;
import com.minhtuan.commercemanager.repository.ProductDetailRepository;
import com.minhtuan.commercemanager.services.ProductDetailService;
import com.minhtuan.commercemanager.services.WarehouseReceiptDetailService;
import com.minhtuan.commercemanager.services.WarehouseReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class WarehouseReceiptDetailAdminController {

    @Autowired
    WarehouseReceiptDetailService warehouseReceiptDetailService;

    @Autowired
    ProductDetailService productDetailService;

    @PostMapping("/add-warehouseReceiptDetail")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> createWarehouseReceiptDetail(@RequestBody WarehouseReceiptDetailRequest warehouseReceiptDetailRequest) {
        warehouseReceiptDetailService.save(warehouseReceiptDetailRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully");
    }

    @GetMapping("/{productId}/{sizeId}/{colorId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getColorByProductIdAndSizeId(@PathVariable(value = "productId") String productId, @PathVariable(value = "sizeId") Integer sizeId, @PathVariable(value = "colorId") Integer colorId) {
        boolean exist = productDetailService.existsByProductIdAndSizeIdAndColorId(productId, sizeId, colorId);
        return ResponseEntity.status(HttpStatus.OK).body(exist);
    }

    @GetMapping("/warehouseReceiptDetail/{warehouseReceiptId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getAllWarehouseReceipt(@PathVariable(value = "warehouseReceiptId") String warehouseReceiptId) {
        List<WarehouseReceiptDetailResponse> warehouseReceiptDetailResponseList = warehouseReceiptDetailService.getAll(warehouseReceiptId);
        return ResponseEntity.ok().body(warehouseReceiptDetailResponseList);
    }
}
