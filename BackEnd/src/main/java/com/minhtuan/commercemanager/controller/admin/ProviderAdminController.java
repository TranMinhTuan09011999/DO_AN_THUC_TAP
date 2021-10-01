package com.minhtuan.commercemanager.controller.admin;

import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.message.request.ProductRequest;
import com.minhtuan.commercemanager.message.request.ProviderRequest;
import com.minhtuan.commercemanager.message.response.ApiResponse;
import com.minhtuan.commercemanager.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class ProviderAdminController {

    @Autowired
    ProviderService providerService;

    @PostMapping("/add-provider")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> createProvider(@RequestBody ProviderDTO providerRequest) {
        ProviderDTO providerDTO = providerService.save(providerRequest);
        return ResponseEntity.ok().body(providerDTO);
    }

    @GetMapping("/provider")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getProvider() {
        List<ProviderDTO> providerDTOS = providerService.getAllProvider();
        return ResponseEntity.ok().body(providerDTOS);
    }

    @GetMapping("/provider/{providerId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getProviderByProviderId(@PathVariable(value = "providerId") String providerId) {
        ProviderDTO providerDTO = providerService.getProviderByProviderId(providerId);
        return ResponseEntity.ok().body(providerDTO);
    }

    @PutMapping("/provider/update/{providerId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> updateproviderByProviderId(@PathVariable(value = "providerId") String providerId, @RequestBody ProviderRequest providerRequest) {
        ProviderDTO providerDTO = providerService.updateProviderByProviderId(providerId, providerRequest);
        return ResponseEntity.ok().body(providerDTO);
    }

    @PutMapping("/provider/delete/{providerId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> deleteProvider(@PathVariable(value = "providerId") String providerId){
        try {
            providerService.deleteProvider(providerId);
            return ResponseEntity.status(HttpStatus.OK).body("Provider already deleted");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }

}
