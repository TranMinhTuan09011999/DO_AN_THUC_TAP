package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.CategoryDTO;
import com.minhtuan.commercemanager.dto.ProductDTO;
import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.maper.ProductMapper;
import com.minhtuan.commercemanager.message.request.ProductRequest;
import com.minhtuan.commercemanager.model.Category;
import com.minhtuan.commercemanager.model.Product;
import com.minhtuan.commercemanager.model.Provider;
import com.minhtuan.commercemanager.model.Room;
import com.minhtuan.commercemanager.repository.CategoryRepository;
import com.minhtuan.commercemanager.repository.ProductRepository;
import com.minhtuan.commercemanager.repository.ProviderRepository;
import com.minhtuan.commercemanager.repository.RoomRepository;
import com.minhtuan.commercemanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO save(ProductRequest productRequest) {
        Product product = new Product();
        List<Product> productList = productRepository.findAll();
        String idNew = "";
        if(productList.size() > 0){
            Integer end = productList.size() - 1;
            String id = productList.get(end).getProductId();
            String IdInt = id.substring(2);
            String IdBegin = id.substring(0,2);
            Integer newIdInt = Integer.parseInt(IdInt);
            newIdInt += 1;
            String newIdString = newIdInt.toString();
            if(newIdString.length() == 1)
            {
                newIdString = "0000" + newIdString;
            }else if(newIdString.length() == 2)
            {
                newIdString = "000" + newIdString;
            }else if(newIdString.length() == 3)
            {
                newIdString = "00" + newIdString;
            }
            else if(newIdString.length() == 4)
            {
                newIdString = "0" + newIdString;
            }
            idNew = IdBegin + newIdString;
        }else {
            idNew = "SP00001";
        }
        product.setProductId(idNew);
        product.setProductName(productRequest.getProductName());
        product.setStatus(productRequest.getStatus());
        product.setDescription(productRequest.getDescription());
        Provider provider = providerRepository.findProviderByProviderId(productRequest.getProviderId());
        product.setProvider(provider);
        Category category = categoryRepository.findCategoryByCategoryId(productRequest.getCategoryId());
        product.setCategory(category);
        productRepository.save(product);
        ProductDTO productDTO = productMapper.toDTO(product);
        return productDTO;
    }

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> productList = productRepository.findAllByOrderByProductIdDesc();
        List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
        return productDTOList;
    }
}
