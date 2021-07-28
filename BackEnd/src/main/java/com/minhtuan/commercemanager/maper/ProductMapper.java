package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.model.Product;
import com.minhtuan.commercemanager.model.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    @Autowired
    private ProviderMaper providerMaper;

    @Autowired
    private CategoryMapper categoryMapper;

    public ProductDTO toDTO(Product product){
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setProductName(product.getProductName());
        dto.setStatus(product.getStatus());
        dto.setDescription(product.getDescription());
        dto.setProviderDTO(providerMaper.toDTO(product.getProvider()));
        dto.setCategoryDTO(categoryMapper.toDTO(product.getCategory()));
        return dto;
    }
}
