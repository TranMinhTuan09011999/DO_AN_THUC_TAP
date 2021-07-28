package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.CategoryDTO;
import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.message.request.CategoryRequest;

import java.util.List;

public interface CategoryService {
    public CategoryDTO save(CategoryRequest categoryRequest);
    public List<CategoryDTO> getAllCategory();
}
