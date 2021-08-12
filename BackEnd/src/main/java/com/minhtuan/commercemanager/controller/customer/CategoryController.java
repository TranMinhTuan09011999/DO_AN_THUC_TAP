package com.minhtuan.commercemanager.controller.customer;

import com.minhtuan.commercemanager.dto.CategoryDTO;
import com.minhtuan.commercemanager.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<?> getCategory() {
        List<CategoryDTO> categoryDTOList = categoryService.getAllCategory();
        return ResponseEntity.ok().body(categoryDTOList);
    }
}
