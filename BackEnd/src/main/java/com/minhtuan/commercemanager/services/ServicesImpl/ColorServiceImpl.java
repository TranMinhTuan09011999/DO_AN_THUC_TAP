package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.ColorDTO;
import com.minhtuan.commercemanager.dto.ProductConditionDTO;
import com.minhtuan.commercemanager.dto.SizeDTO;
import com.minhtuan.commercemanager.maper.ColorMapper;
import com.minhtuan.commercemanager.maper.SizeMapper;
import com.minhtuan.commercemanager.message.response.ColorByProductIdAndSizeIdResponse;
import com.minhtuan.commercemanager.model.Color;
import com.minhtuan.commercemanager.model.Size;
import com.minhtuan.commercemanager.repository.ColorRepository;
import com.minhtuan.commercemanager.repository.SizeRepository;
import com.minhtuan.commercemanager.services.ColorService;
import com.minhtuan.commercemanager.services.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private ColorMapper colorMapper;

    @Override
    public List<ColorDTO> getAllColor() {
        List<Color> colorList = colorRepository.findAllByOrderByColorId();
        List<ColorDTO> colorDTOList = colorList.stream().map(color -> colorMapper.toDTO(color)).collect(Collectors.toList());
        return colorDTOList;
    }

    @Override
    public ColorDTO getColorByColorId(Integer colorId) {
        Color color = colorRepository.findColorById(colorId);
        ColorDTO colorDTO = colorMapper.toDTO(color);
        return colorDTO;
    }

    @Override
    public List<ColorByProductIdAndSizeIdResponse> getColorByProductIdAndSizeId(String productId, Integer sizeId, Integer colorId) {
        List<ColorByProductIdAndSizeIdResponse> colorByProductIdAndSizeIdResponseList = colorRepository.getColorByProductIdAndSizeId(productId, sizeId, colorId).stream().map(ColorByProductIdAndSizeIdResponse::new).collect(Collectors.toList());
        return colorByProductIdAndSizeIdResponseList;
    }
}
