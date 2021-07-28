package com.minhtuan.commercemanager.controller.admin;

import com.minhtuan.commercemanager.dto.CategoryDTO;
import com.minhtuan.commercemanager.dto.SizeDTO;
import com.minhtuan.commercemanager.message.request.CategoryRequest;
import com.minhtuan.commercemanager.services.CategoryService;
import com.minhtuan.commercemanager.services.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class SizeAdminCotroller {
    @Autowired
    SizeService sizeService;

    @GetMapping("/size")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> getSize() {
        List<SizeDTO> sizeDTOList = sizeService.getAllSize();
        return ResponseEntity.ok().body(sizeDTOList);
    }
}
