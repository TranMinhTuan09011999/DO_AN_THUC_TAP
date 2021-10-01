package com.minhtuan.commercemanager.controller.customer;

import com.minhtuan.commercemanager.dto.ProductByRoomDTO;
import com.minhtuan.commercemanager.dto.ProductConditionDTO;
import com.minhtuan.commercemanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class ProductCustomerController {

    @Autowired
    ProductService productService;

    @GetMapping("/productOderby/{roomId}/{categoryId}/{orderBy}")
    public ResponseEntity<?> getAllProductWithRoomIdAndCategoryId(@PathVariable(value = "roomId") Integer roomId, @PathVariable(value = "categoryId") String categoryId, @PathVariable(value = "orderBy") Integer orderBy){
        List<ProductConditionDTO> productConditionDTOList = productService.getProductWithCondition(categoryId, roomId, orderBy);
        return ResponseEntity.ok().body(productConditionDTOList);
    }

    @GetMapping("/getcolor/{roomId}/{categoryId}/{colorId}")
    public ResponseEntity<?> getAllProductWithRoomIdAndCategoryIdAndColorId(@PathVariable(value = "roomId") Integer roomId, @PathVariable(value = "categoryId") String categoryId, @PathVariable(value = "colorId") Integer colorId){
        List<ProductConditionDTO> productConditionDTOList = productService.getProductWithConditionColor(categoryId, roomId, colorId);
        return ResponseEntity.ok().body(productConditionDTOList);
    }

    @GetMapping("/getPrice/{roomId}/{categoryId}/{minValue}/{maxValue}")
    public ResponseEntity<?> getAllProductWithRoomIdAndCategoryIdAndPrice(@PathVariable(value = "roomId") Integer roomId, @PathVariable(value = "categoryId") String categoryId, @PathVariable(value = "minValue") Double minValue, @PathVariable(value = "maxValue") Double maxValue){
        List<ProductConditionDTO> productConditionDTOList = productService.getProductWithConditionPrice(categoryId, roomId, minValue, maxValue);
        return ResponseEntity.ok().body(productConditionDTOList);
    }

    @GetMapping("/product/room/{roomId}")
    public ResponseEntity<?> getTop8NewProduct(@PathVariable(value = "roomId") Integer roomId){
        List<ProductConditionDTO> productByRoomDTOList = productService.getTop8NewProduct(roomId);
        return ResponseEntity.ok().body(productByRoomDTOList);
    }

    @GetMapping("/product/room/discount/{roomId}")
    public ResponseEntity<?> getTop8DiscountProduct(@PathVariable(value = "roomId") Integer roomId){
        List<ProductConditionDTO> productByRoomDTOList = productService.getTop8DiscountProduct(roomId);
        return ResponseEntity.ok().body(productByRoomDTOList);
    }
}
