package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.ProductByRoomDTO;
import com.minhtuan.commercemanager.dto.ProductConditionDTO;
import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProductDetailDTO;
import com.minhtuan.commercemanager.exception.ResourceNotFoundException;
import com.minhtuan.commercemanager.maper.ProductDetailMapper;
import com.minhtuan.commercemanager.message.request.ProductDetailRequest;
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

import java.util.ArrayList;
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

    @Override
    public ProductDetailDTO updateProductDetailByProductDetailId(Integer productDetailId, ProductDetailRequest productDetailRequest) {
        ProductDetail productDetail = productDetailRepository.getById(productDetailId);
        productDetail.setQuantity(productDetailRequest.getQuantity());
        productDetail.setPrice(productDetailRequest.getPrice());
        productDetail.setDiscount(productDetailRequest.getDiscount());
        productDetail.setImage(productDetailRequest.getImage());
        Product product = productRepository.findProductByProductId(productDetailRequest.getProductId());
        productDetail.setProduct(product);
        Size size = sizeRepository.findSizeBySizeId(productDetailRequest.getSizeId());
        productDetail.setSize(size);
        Color color = colorRepository.findColorById(productDetailRequest.getColorId());
        productDetail.setColor(color);
        productDetailRepository.save(productDetail);
        ProductDetailDTO productDetailDTO = productDetailMapper.toDTO(productDetail);
        return productDetailDTO;
    }

    @Override
    public ProductDetailDTO getProductDetailWithProductDetailId(Integer productDtailId) {
        ProductDetail productDetail = productDetailRepository.getById(productDtailId);
        ProductDetailDTO productDetailDTO = productDetailMapper.toDTO(productDetail);
        return productDetailDTO;
    }

    @Override
    public List<ProductDetailDTO> getAllProductDetailBySearch(Integer sizeId, Integer colorId, String productId) {
        List<ProductDetailDTO> productDetailDTOList = new ArrayList<>();
        if(sizeId != 0 && colorId == 0){
            Size size = sizeRepository.getById(sizeId);
            Product product = productRepository.getById(productId);
            List<ProductDetail> productDetailList = productDetailRepository.findAllBySizeAndProductOrderByProductDetailIdDesc(size, product);
            productDetailDTOList = productDetailList.stream().map(productDetail -> productDetailMapper.toDTO(productDetail)).collect(Collectors.toList());
            return productDetailDTOList;
        }else if(sizeId == 0 && colorId !=0) {
            Color color = colorRepository.getById(colorId);
            Product product = productRepository.getById(productId);
            List<ProductDetail> productDetailList = productDetailRepository.findAllByColorAndProductOrderByProductDetailIdDesc(color, product);
            productDetailDTOList = productDetailList.stream().map(productDetail -> productDetailMapper.toDTO(productDetail)).collect(Collectors.toList());
            return productDetailDTOList;
        }else if(sizeId != 0 && colorId != 0){
            Product product = productRepository.getById(productId);
            Size size = sizeRepository.getById(sizeId);
            Color color = colorRepository.getById(colorId);
            List<ProductDetail> productDetailList = productDetailRepository.findAllBySizeAndColorAndProductOrderByProductDetailIdDesc(size, color, product);
            productDetailDTOList = productDetailList.stream().map(productDetail -> productDetailMapper.toDTO(productDetail)).collect(Collectors.toList());
            return productDetailDTOList;
        }
        return productDetailDTOList;
    }

    @Override
    public ProductDetailDTO updateQuantityAndPrice(Integer productDetailId, Integer quantity, Double price) {
        ProductDetail productDetail = productDetailRepository.getById(productDetailId);
        Integer oldQuantity = productDetail.getQuantity();
        productDetail.setQuantity(oldQuantity + quantity);
        productDetail.setPrice(price);
        ProductDetail productDetail1 = productDetailRepository.save(productDetail);
        ProductDetailDTO productDetailDTO = productDetailMapper.toDTO(productDetail1);
        return  productDetailDTO;
    }

    @Override
    public ProductDetailDTO updateQuantityProductDetail(Integer productDetailId, Integer quantity) {
        ProductDetail productDetail = productDetailRepository.getById(productDetailId);
        Integer oldQuantity = productDetail.getQuantity();
        productDetail.setQuantity(oldQuantity - quantity);
        ProductDetail productDetail1 = productDetailRepository.save(productDetail);
        ProductDetailDTO productDetailDTO = productDetailMapper.toDTO(productDetail1);
        return  productDetailDTO;
    }
}
