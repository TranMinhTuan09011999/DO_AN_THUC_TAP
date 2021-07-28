package com.minhtuan.commercemanager.controller.admin;

import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class ProviderAdminController {

    @Autowired
    ProviderService providerService;

    @PostMapping("/add-provider")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> createProvider(@RequestBody ProviderDTO providerRequest) {
        ProviderDTO providerDTO = providerService.save(providerRequest);
        return ResponseEntity.ok().body(providerDTO);
    }

    @GetMapping("/provider")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> getProvider() {
        List<ProviderDTO> providerDTOS = providerService.getAllProvider();
        return ResponseEntity.ok().body(providerDTOS);
    }

}
