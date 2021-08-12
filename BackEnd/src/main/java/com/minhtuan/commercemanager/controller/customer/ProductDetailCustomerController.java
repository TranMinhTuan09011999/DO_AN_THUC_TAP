package com.minhtuan.commercemanager.controller.customer;

import com.minhtuan.commercemanager.dto.ProductConditionDTO;
import com.minhtuan.commercemanager.dto.ProductDetailDTO;
import com.minhtuan.commercemanager.exception.ResourceNotFoundException;
import com.minhtuan.commercemanager.maper.ProductDetailMapper;
import com.minhtuan.commercemanager.message.response.CartResponse;
import com.minhtuan.commercemanager.model.ProductDetail;
import com.minhtuan.commercemanager.repository.ProductDetailRepository;
import com.minhtuan.commercemanager.services.ProductDetailService;
import com.minhtuan.commercemanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class ProductDetailCustomerController {

    @Autowired
    ProductDetailService productDetailService;

    @Autowired
    ProductDetailMapper productDetailMapper;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @GetMapping("/{productId}/{colorId}/{sizeId}")
    public ResponseEntity<?> getProductDetailWithPoductIdAndColorIdAndSizeId(@PathVariable(value = "productId") String productId, @PathVariable(value = "colorId") Integer colorId, @PathVariable(value = "sizeId") Integer sizeId){
        List<ProductConditionDTO> productConditionDTOList = productDetailService.getProductDetailWithProductIdAndColorIdAndSizeId(productId, colorId, sizeId);
        return ResponseEntity.ok().body(productConditionDTOList);
    }

    @GetMapping("/product/{sizeId}")
    public ResponseEntity<?> getColorBySizeId(@PathVariable(value = "sizeId") Integer sizeId){
        List<ProductDetailDTO> productDetailDTOList = productDetailService.getfirstProductDetailBySizeId(sizeId);
        return ResponseEntity.ok().body(productDetailDTOList);
    }

    @GetMapping("/product-detail/{productDetailId}")
    public ResponseEntity<?> getProductDetailByProductDetailId (@PathVariable(value = "productDetailId") Integer productDetailId) throws ResourceNotFoundException {
        List<CartResponse> cartResponseList = productDetailService.getProductDetailByProductDetailId(productDetailId);
        return ResponseEntity.ok().body(cartResponseList.get(0));
    }
}
