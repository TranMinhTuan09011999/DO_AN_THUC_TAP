package com.minhtuan.commercemanager.controller.admin;

import com.minhtuan.commercemanager.model.ProductDetail;
import com.minhtuan.commercemanager.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import java.nio.file.Files;
import java.nio.file.Paths;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    ServletContext context;
    @Autowired
    ProductDetailRepository productDetailRepository;


    @GetMapping(path="/imageProduct/{id}")
    public byte[] getPhoto(@PathVariable("id") Integer id) throws Exception{
        ProductDetail productDetail   = productDetailRepository.findByProductDetailId(id).get();
        return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+productDetail.getImage()));
    }
}
