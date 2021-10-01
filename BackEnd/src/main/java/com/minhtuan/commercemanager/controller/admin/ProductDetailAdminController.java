package com.minhtuan.commercemanager.controller.admin;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProductDetailDTO;
import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.exception.ResourceNotFoundException;
import com.minhtuan.commercemanager.maper.ProductDetailMapper;
import com.minhtuan.commercemanager.message.request.ProductDetailRequest;
import com.minhtuan.commercemanager.message.request.ProductRequest;
import com.minhtuan.commercemanager.message.response.ApiResponse;
import com.minhtuan.commercemanager.message.response.MessageResponse;
import com.minhtuan.commercemanager.message.response.OrderResponse;
import com.minhtuan.commercemanager.model.*;
import com.minhtuan.commercemanager.repository.ColorRepository;
import com.minhtuan.commercemanager.repository.ProductDetailRepository;
import com.minhtuan.commercemanager.repository.ProductRepository;
import com.minhtuan.commercemanager.repository.SizeRepository;
import com.minhtuan.commercemanager.services.ProductDetailService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class ProductDetailAdminController {

    @Autowired
    ServletContext context;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    SizeRepository sizeRepository;

    @Autowired
    ColorRepository colorRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductDetailService productDetailService;

    @Autowired
    ProductDetailMapper productDetailMapper;

    @PostMapping("/add-product-detail")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> createProductDetail (@RequestParam("file") MultipartFile file,
                                            @RequestParam("productDetail") String  productDetail) throws JsonParseException, JsonMappingException, Exception
    {
        System.out.println("Ok .............");
        ProductDetailRequest productDetailRequest = new ObjectMapper().readValue(productDetail, ProductDetailRequest.class);
        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/")).mkdir();
            System.out.println("mk dir.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

        }catch(Exception e) {
            e.printStackTrace();
        }


        productDetailRequest.setImage(newFileName);
        ProductDetail productDetail1 = new ProductDetail();
        productDetail1.setQuantity(productDetailRequest.getQuantity());
        productDetail1.setPrice(productDetailRequest.getPrice());
        productDetail1.setImage(productDetailRequest.getImage());
        productDetail1.setDiscount(productDetailRequest.getDiscount());
        Size size = sizeRepository.findSizeBySizeId(productDetailRequest.getSizeId());
        productDetail1.setSize(size);
        Color color = colorRepository.findColorById(productDetailRequest.getColorId());
        productDetail1.setColor(color);
        Product product = productRepository.findProductByProductId(productDetailRequest.getProductId());
        productDetail1.setProduct(product);
        productDetailRepository.save(productDetail1);
        if (productDetail1 != null)
        {
            return new ResponseEntity<MessageResponse>(new MessageResponse (""), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<MessageResponse>(new MessageResponse("Product detail not saved"),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/product-detail/update/{productDetailId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> updateProductDetail (@PathVariable(value = "productDetailId") Integer productDetailId, @RequestParam("file") MultipartFile file,
                                                  @RequestParam("productDetail") String  productDetail) throws JsonParseException, JsonMappingException, Exception
    {
        System.out.println("Ok .............");
        ProductDetailRequest productDetailRequest = new ObjectMapper().readValue(productDetail, ProductDetailRequest.class);
        boolean isExit = new File(context.getRealPath("/Images/")).exists();
        if (!isExit)
        {
            new File (context.getRealPath("/Images/")).mkdir();
            System.out.println("mk dir.............");
        }
        String filename = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
        File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
        try
        {
            System.out.println("Image");
            FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

        }catch(Exception e) {
            e.printStackTrace();
        }


        productDetailRequest.setImage(newFileName);
        ProductDetail productDetail1 = productDetailRepository.getById(productDetailId);
        productDetail1.setQuantity(productDetailRequest.getQuantity());
        productDetail1.setPrice(productDetailRequest.getPrice());
        productDetail1.setImage(productDetailRequest.getImage());
        productDetail1.setDiscount(productDetailRequest.getDiscount());
        Size size = sizeRepository.findSizeBySizeId(productDetailRequest.getSizeId());
        productDetail1.setSize(size);
        Color color = colorRepository.findColorById(productDetailRequest.getColorId());
        productDetail1.setColor(color);
        Product product = productRepository.findProductByProductId(productDetailRequest.getProductId());
        productDetail1.setProduct(product);
        productDetailRepository.save(productDetail1);
        if (productDetail1 != null)
        {
            return new ResponseEntity<MessageResponse>(new MessageResponse (""), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<MessageResponse>(new MessageResponse("Product detail not saved"),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/product-detail/update/noChangeImage/{productDetailId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> updateProductDetailWithoutImage (@PathVariable(value = "productDetailId") Integer productDetailId, @RequestBody ProductDetailRequest productDetailRequest) throws JsonParseException, JsonMappingException, Exception
    {
        ProductDetail productDetail1 = productDetailRepository.getById(productDetailId);
        productDetail1.setQuantity(productDetailRequest.getQuantity());
        productDetail1.setPrice(productDetailRequest.getPrice());
        productDetail1.setImage(productDetailRequest.getImage());
        productDetail1.setDiscount(productDetailRequest.getDiscount());
        Size size = sizeRepository.findSizeBySizeId(productDetailRequest.getSizeId());
        productDetail1.setSize(size);
        Color color = colorRepository.findColorById(productDetailRequest.getColorId());
        productDetail1.setColor(color);
        Product product = productRepository.findProductByProductId(productDetailRequest.getProductId());
        productDetail1.setProduct(product);
        productDetailRepository.save(productDetail1);
        if (productDetail1 != null)
        {
            return new ResponseEntity<MessageResponse>(new MessageResponse (""), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<MessageResponse>(new MessageResponse("Product detail not saved"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product-detail/{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getAllProductDetailByProductId (@PathVariable(value = "productId") String productId) throws ResourceNotFoundException {
        System.out.println("aaa");
        List<ProductDetailDTO> productDetailDTOList = productDetailService.getAllProductDetail(productId);
        return ResponseEntity.ok().body(productDetailDTOList);
    }

    @GetMapping("/productDetail/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> getProductDetailBySearch(@Param(value = "sizeId") Integer sizeId,
                                                   @Param(value = "colorId") Integer colorId,
                                                      @Param(value = "productId") String productId) {
        List<ProductDetailDTO> productDetailDTOList = productDetailService.getAllProductDetailBySearch(sizeId, colorId, productId);
        return ResponseEntity.ok().body(productDetailDTOList);
    }

    @PutMapping("/productDetail/update/{productDetailId}/{quantity}/{price}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> updateProductDetailByQuantityAndPrice (@PathVariable(value = "productDetailId") Integer productDetailId, @PathVariable(value = "quantity") Integer quantity, @PathVariable(value = "price") Double price) { ;
        ProductDetailDTO productDetailDTO = productDetailService.updateQuantityAndPrice(productDetailId, quantity, price);
        return ResponseEntity.ok().body(productDetailDTO);
    }

    @PutMapping("/productDetail/update/{productDetailId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE')")
    public ResponseEntity<?> updateProductDetailByQuantity(@PathVariable(value = "productDetailId") Integer productDetailId, @RequestBody HashMap<String, Integer> quantity) {
        try {
            Integer quantity1 = quantity.get("quantity");
            ProductDetailDTO productDetailDTO = productDetailService.updateQuantityProductDetail(productDetailId,quantity1);
            return ResponseEntity.status(HttpStatus.OK).body(productDetailDTO);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), ""));
        }
    }
}
