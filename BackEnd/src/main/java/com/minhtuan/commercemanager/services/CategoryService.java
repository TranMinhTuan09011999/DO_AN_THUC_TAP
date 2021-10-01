package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.CategoryDTO;
import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.message.request.CategoryRequest;
import com.minhtuan.commercemanager.message.request.ProviderRequest;

import java.util.List;

public interface CategoryService {
    public CategoryDTO save(CategoryRequest categoryRequest);
    public List<CategoryDTO> getAllCategory();
    public List<CategoryDTO> getAllCategoryByRoom(Integer roomId);
    public CategoryDTO getCategoryByCategoryId(String categoryId);
    public CategoryDTO updateCategoryByCategoryId(String categoryId, CategoryRequest categoryRequest);
    public void deleteCategory(String categoryId);
}
