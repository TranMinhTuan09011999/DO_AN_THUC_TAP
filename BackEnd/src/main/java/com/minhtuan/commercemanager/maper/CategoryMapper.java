package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.dto.CategoryDTO;
import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.model.Category;
import com.minhtuan.commercemanager.model.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    @Autowired
    private RoomMapper roomMapper;

    public CategoryDTO toDTO(Category category){
        CategoryDTO dto = new CategoryDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setCategoryName(category.getCategoryName());
        dto.setRoom(roomMapper.toDTO(category.getRoom()));
        return dto;
    }
}
