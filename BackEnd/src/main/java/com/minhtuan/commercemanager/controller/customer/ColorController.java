package com.minhtuan.commercemanager.controller.customer;

import com.minhtuan.commercemanager.dto.CategoryDTO;
import com.minhtuan.commercemanager.message.response.ColorByProductIdAndSizeIdResponse;
import com.minhtuan.commercemanager.services.CategoryService;
import com.minhtuan.commercemanager.services.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class ColorController {

    @Autowired
    ColorService colorService;

    @GetMapping("/color/{productId}/{sizeId}/{colorId}")
    public ResponseEntity<?> getColorByProductIdAndSizeId(@PathVariable(value = "productId") String productId, @PathVariable(value = "sizeId") Integer sizeId, @PathVariable(value = "colorId") Integer colorId) {
        List<ColorByProductIdAndSizeIdResponse> colorByProductIdAndSizeIdResponseList = colorService.getColorByProductIdAndSizeId(productId, sizeId, colorId);
        return ResponseEntity.ok().body(colorByProductIdAndSizeIdResponseList);
    }

}
