package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.ColorDTO;
import com.minhtuan.commercemanager.dto.SizeDTO;
import com.minhtuan.commercemanager.message.response.ColorByProductIdAndSizeIdResponse;

import java.util.List;

public interface ColorService {
    public List<ColorDTO> getAllColor();
    public ColorDTO getColorByColorId(Integer colorId);
    public  List<ColorByProductIdAndSizeIdResponse> getColorByProductIdAndSizeId(String productId, Integer sizeId, Integer colorId);
}
