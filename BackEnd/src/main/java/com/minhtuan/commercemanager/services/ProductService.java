package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.ProductByRoomDTO;
import com.minhtuan.commercemanager.dto.ProductConditionDTO;
import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.message.request.ProductRequest;

import java.util.List;

public interface ProductService {
    public ProductDTO save(ProductRequest productRequest);
    public List<ProductDTO> getAllProduct();
    public List<ProductConditionDTO> getProductWithCondition(String categoryId, Integer roomId, Integer orderBy);


    public List<ProductConditionDTO> getProductWithConditionColor(String categoryId, Integer roomId, Integer colorId);
    public List<ProductConditionDTO> getProductWithConditionPrice(String categoryId, Integer roomId, Double minValue, Double maxValue);
    public List<ProductConditionDTO> getTop8NewProduct(Integer roomId);
    public List<ProductConditionDTO> getTop8DiscountProduct(Integer roomId);
    public ProductDTO getProductByProductId(String productId);
    public ProductDTO updateProductByProductId(String productId, ProductRequest productRequest);
    public List<ProductDTO> getAllProductBySearch(String productId, String productName, String categoryId, String providerId, Integer status);
    public Long newProductCount();
}
