package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProductDetailDTO;
import com.minhtuan.commercemanager.exception.ResourceNotFoundException;
import com.minhtuan.commercemanager.model.Product;

import java.util.List;

public interface ProductDetailService {
    public List<ProductDetailDTO> getAllProductDetail(String productId) throws ResourceNotFoundException;
}
