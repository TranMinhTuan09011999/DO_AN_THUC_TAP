package com.minhtuan.commercemanager.controller.customer;

import com.minhtuan.commercemanager.dto.CategoryDTO;
import com.minhtuan.commercemanager.dto.ColorDTO;
import com.minhtuan.commercemanager.message.response.ColorByProductIdAndSizeIdResponse;
import com.minhtuan.commercemanager.services.CategoryService;
import com.minhtuan.commercemanager.services.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
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

    @GetMapping("/color")
    public ResponseEntity<?> getColor() {
        List<ColorDTO> colorDTOList = colorService.getAllColor();
        return ResponseEntity.ok().body(colorDTOList);
    }

}
