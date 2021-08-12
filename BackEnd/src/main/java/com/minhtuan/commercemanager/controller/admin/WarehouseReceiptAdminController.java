package com.minhtuan.commercemanager.controller.admin;

import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.dto.SizeDTO;
import com.minhtuan.commercemanager.message.request.WarehouseReceiptRequest;
import com.minhtuan.commercemanager.message.response.WarehouseReceiptResponse;
import com.minhtuan.commercemanager.model.WarehouseReceipt;
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
public class WarehouseReceiptAdminController {

    @Autowired
    WarehouseReceiptService warehouseReceiptService;

    @PostMapping("/add-warehouseReceipt")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> createWarehouseReceipt(@RequestBody WarehouseReceiptRequest warehouseReceiptRequest) {
        String warehouseReceiptId = warehouseReceiptService.save(warehouseReceiptRequest);
        return ResponseEntity.status(HttpStatus.OK).body(warehouseReceiptId);
    }

    @GetMapping("/warehouseReceipt")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> getAllWarehouseReceipt() {
        List<WarehouseReceiptResponse> warehouseReceiptResponseList = warehouseReceiptService.getAll();
        return ResponseEntity.ok().body(warehouseReceiptResponseList);
    }

}
