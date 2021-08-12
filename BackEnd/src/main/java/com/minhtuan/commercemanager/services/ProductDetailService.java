package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.ProductConditionDTO;
import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProductDetailDTO;
import com.minhtuan.commercemanager.exception.ResourceNotFoundException;
import com.minhtuan.commercemanager.message.response.CartResponse;
import com.minhtuan.commercemanager.model.Product;

import java.util.List;

public interface ProductDetailService {
    public List<ProductDetailDTO> getAllProductDetail(String productId) throws ResourceNotFoundException;
    public List<ProductConditionDTO> getProductDetailWithProductIdAndColorIdAndSizeId(String productId, Integer colorId, Integer sizeId);
    public List<ProductDetailDTO> getfirstProductDetailBySizeId(Integer sizeId);
    public List<CartResponse> getProductDetailByProductDetailId(Integer productDetailId);
    Boolean existsByProductIdAndSizeIdAndColorId(String productId, Integer sizeId, Integer colorId);
}
