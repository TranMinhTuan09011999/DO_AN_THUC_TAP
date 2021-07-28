package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.CategoryDTO;
import com.minhtuan.commercemanager.dto.SizeDTO;
import com.minhtuan.commercemanager.maper.SizeMapper;
import com.minhtuan.commercemanager.model.Category;
import com.minhtuan.commercemanager.model.Size;
import com.minhtuan.commercemanager.repository.CategoryRepository;
import com.minhtuan.commercemanager.repository.SizeRepository;
import com.minhtuan.commercemanager.services.CategoryService;
import com.minhtuan.commercemanager.services.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private SizeMapper sizeMapper;

    @Override
    public List<SizeDTO> getAllSize() {
        List<Size> sizeList = sizeRepository.findAllByOrderBySizeId();
        List<SizeDTO> sizeDTOList = sizeList.stream().map(size -> sizeMapper.toDTO(size)).collect(Collectors.toList());
        return sizeDTOList;
    }
}
