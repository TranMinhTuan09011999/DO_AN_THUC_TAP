package com.minhtuan.commercemanager.controller.admin;

import com.minhtuan.commercemanager.dto.ColorDTO;
import com.minhtuan.commercemanager.dto.SizeDTO;
import com.minhtuan.commercemanager.services.ColorService;
import com.minhtuan.commercemanager.services.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class ColorAdminCotroller {
    @Autowired
    ColorService colorService;

    @GetMapping("/color")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getColor() {
        List<ColorDTO> colorDTOList = colorService.getAllColor();
        return ResponseEntity.ok().body(colorDTOList);
    }

    @GetMapping("/color/{colorId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getSizeBySizeId(@PathVariable(value = "colorId") Integer colorId) {
        ColorDTO colorDTO = colorService.getColorByColorId(colorId);
        return ResponseEntity.ok().body(colorDTO);
    }
}
