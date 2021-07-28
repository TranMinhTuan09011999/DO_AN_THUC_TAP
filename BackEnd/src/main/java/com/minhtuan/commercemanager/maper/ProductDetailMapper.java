package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProductDetailDTO;
import com.minhtuan.commercemanager.model.Product;
import com.minhtuan.commercemanager.model.ProductDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailMapper {
    @Autowired
    private SizeMapper sizeMapper;

    @Autowired
    private ColorMapper colorMapper;

    @Autowired
    private ProductMapper productMapper;

    public ProductDetailDTO toDTO(ProductDetail productDetail){
        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setProductDetailId(productDetail.getProductDetailId());
        dto.setQuantity(productDetail.getQuantity());
        dto.setPrice(productDetail.getPrice());
        dto.setImage(productDetail.getImage());
        dto.setSize(sizeMapper.toDTO(productDetail.getSize()));
        dto.setColor(colorMapper.toDTO(productDetail.getColor()));
        dto.setProduct(productMapper.toDTO(productDetail.getProduct()));
        return dto;
    }
}
