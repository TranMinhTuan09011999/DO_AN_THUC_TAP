package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.dto.ColorDTO;
import com.minhtuan.commercemanager.dto.SizeDTO;
import com.minhtuan.commercemanager.model.Color;
import com.minhtuan.commercemanager.model.Size;
import org.springframework.stereotype.Component;

@Component
public class ColorMapper {
    public ColorDTO toDTO(Color color){
        ColorDTO dto = new ColorDTO();
        dto.setColorId(color.getColorId());
        dto.setColorName(color.getColorName());
        return dto;
    }
}
