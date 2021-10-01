package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.ProductByRoomDTO;
import com.minhtuan.commercemanager.dto.ProductConditionDTO;
import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProductDetailDTO;
import com.minhtuan.commercemanager.exception.ResourceNotFoundException;
import com.minhtuan.commercemanager.message.request.ProductDetailRequest;
import com.minhtuan.commercemanager.message.response.CartResponse;
import com.minhtuan.commercemanager.model.Product;

import java.util.List;

public interface ProductDetailService {
    public List<ProductDetailDTO> getAllProductDetail(String productId) throws ResourceNotFoundException;
    public List<ProductConditionDTO> getProductDetailWithProductIdAndColorIdAndSizeId(String productId, Integer colorId, Integer sizeId);
    public List<ProductDetailDTO> getfirstProductDetailBySizeId(Integer sizeId);
    public List<CartResponse> getProductDetailByProductDetailId(Integer productDetailId);
    Boolean existsByProductIdAndSizeIdAndColorId(String productId, Integer sizeId, Integer colorId);
    public ProductDetailDTO updateProductDetailByProductDetailId(Integer productDetailId, ProductDetailRequest productDetailRequest);
    public ProductDetailDTO getProductDetailWithProductDetailId(Integer productDtailId);
    public List<ProductDetailDTO> getAllProductDetailBySearch(Integer sizeId, Integer colorId, String productId);
    public ProductDetailDTO updateQuantityAndPrice(Integer productDetailId, Integer quantity, Double price);
    public ProductDetailDTO updateQuantityProductDetail(Integer productDetailId, Integer quantity);
}
