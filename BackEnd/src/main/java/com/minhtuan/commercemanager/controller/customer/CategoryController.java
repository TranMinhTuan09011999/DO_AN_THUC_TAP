package com.minhtuan.commercemanager.controller.customer;

import com.minhtuan.commercemanager.dto.CategoryDTO;
import com.minhtuan.commercemanager.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/category/{roomId}")
    public ResponseEntity<?> getCategoryByRoom(@PathVariable(value = "roomId") Integer roomId) {
        List<CategoryDTO> categoryDTOList = categoryService.getAllCategoryByRoom(roomId);
        return ResponseEntity.ok().body(categoryDTOList);
    }
}
