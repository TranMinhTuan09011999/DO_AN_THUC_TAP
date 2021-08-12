package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.ProductConditionDTO;
import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.message.request.ProductRequest;

import java.util.List;

public interface ProductService {
    public ProductDTO save(ProductRequest productRequest);
    public List<ProductDTO> getAllProduct();
    public List<ProductConditionDTO> getProductWithCondition(String categoryId, Integer roomId);
}
