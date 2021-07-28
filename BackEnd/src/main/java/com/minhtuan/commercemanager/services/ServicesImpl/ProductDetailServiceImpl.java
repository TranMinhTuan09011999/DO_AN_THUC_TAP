package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProductDetailDTO;
import com.minhtuan.commercemanager.exception.ResourceNotFoundException;
import com.minhtuan.commercemanager.maper.ProductDetailMapper;
import com.minhtuan.commercemanager.model.Product;
import com.minhtuan.commercemanager.model.ProductDetail;
import com.minhtuan.commercemanager.repository.ProductDetailRepository;
import com.minhtuan.commercemanager.repository.ProductRepository;
import com.minhtuan.commercemanager.services.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    ProductDetailMapper productDetailMapper;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductDetailDTO> getAllProductDetail(String productId) throws ResourceNotFoundException {
        Product product = productRepository.findProductsByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        List<ProductDetail> productDetailList = productDetailRepository.findAllByProduct(product);
        List<ProductDetailDTO> productDetailDTOList = productDetailList.stream().map(productDetail -> productDetailMapper.toDTO(productDetail)).collect(Collectors.toList());
        return productDetailDTOList;
    }
}
