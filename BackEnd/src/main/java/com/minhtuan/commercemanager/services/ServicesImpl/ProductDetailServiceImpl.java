package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.ProductConditionDTO;
import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProductDetailDTO;
import com.minhtuan.commercemanager.exception.ResourceNotFoundException;
import com.minhtuan.commercemanager.maper.ProductDetailMapper;
import com.minhtuan.commercemanager.message.response.CartResponse;
import com.minhtuan.commercemanager.message.response.ColorByProductIdAndSizeIdResponse;
import com.minhtuan.commercemanager.model.Color;
import com.minhtuan.commercemanager.model.Product;
import com.minhtuan.commercemanager.model.ProductDetail;
import com.minhtuan.commercemanager.model.Size;
import com.minhtuan.commercemanager.repository.ColorRepository;
import com.minhtuan.commercemanager.repository.ProductDetailRepository;
import com.minhtuan.commercemanager.repository.ProductRepository;
import com.minhtuan.commercemanager.repository.SizeRepository;
import com.minhtuan.commercemanager.services.ProductDetailService;
import com.minhtuan.commercemanager.services.SizeService;
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

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    ColorRepository colorRepository;

    @Override
    public List<ProductDetailDTO> getAllProductDetail(String productId) throws ResourceNotFoundException {
        Product product = productRepository.findProductsByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
        List<ProductDetail> productDetailList = productDetailRepository.findAllByProduct(product);
        List<ProductDetailDTO> productDetailDTOList = productDetailList.stream().map(productDetail -> productDetailMapper.toDTO(productDetail)).collect(Collectors.toList());
        return productDetailDTOList;
    }

    @Override
    public List<ProductConditionDTO> getProductDetailWithProductIdAndColorIdAndSizeId(String productId, Integer colorId, Integer sizeId) {
        List<ProductConditionDTO> productConditionDTOList = productDetailRepository.getProductDetailWithProductIdAndColorIdAndSizeId(productId, colorId, sizeId).stream().map(ProductConditionDTO::new).collect(Collectors.toList());
        return productConditionDTOList;
    }

    @Override
    public List<ProductDetailDTO> getfirstProductDetailBySizeId(Integer sizeId){
        Size size = sizeRepository.findSizeBySizeId(sizeId);
        List<ProductDetail> productDetailList = productDetailRepository.findFirstBySize(size);
        List<ProductDetailDTO> productDetailDTOList = productDetailList.stream().map(productDetail -> productDetailMapper.toDTO(productDetail)).collect(Collectors.toList());
        return productDetailDTOList;
    }

    @Override
    public List<CartResponse> getProductDetailByProductDetailId(Integer productDetailId) {
        List<CartResponse> cartResponseList = productDetailRepository.getProductDetailWithProductDetailId(productDetailId).stream().map(CartResponse::new).collect(Collectors.toList());
        return cartResponseList;
    }

    @Override
    public Boolean existsByProductIdAndSizeIdAndColorId(String productId, Integer sizeId, Integer colorId) {
        Product product = productRepository.findProductByProductId(productId);
        Size size = sizeRepository.findSizeBySizeId(sizeId);
        Color color = colorRepository.findColorById(colorId);
        ProductDetail productDetail = productDetailRepository.findProductDetailByProductAndSizeAndColor(product, size, color);
        if(productDetail == null){
            return false;
        }else{
            return true;
        }
    }
}
