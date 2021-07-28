package com.minhtuan.commercemanager.controller.admin;

import com.minhtuan.commercemanager.dto.CategoryDTO;
import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.message.request.CategoryRequest;
import com.minhtuan.commercemanager.services.CategoryService;
import com.minhtuan.commercemanager.services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class CategoryAdminController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/add-category")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryDTO categoryDTO = categoryService.save(categoryRequest);
        return ResponseEntity.ok().body(categoryDTO);
    }

    @GetMapping("/category")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> getCategory() {
        List<CategoryDTO> categoryDTOList = categoryService.getAllCategory();
        return ResponseEntity.ok().body(categoryDTOList);
    }
}
