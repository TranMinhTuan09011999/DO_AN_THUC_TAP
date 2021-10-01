package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.*;
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

import java.util.ArrayList;
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

    @Override
    public List<ProductConditionDTO> getProductWithCondition(String categoryId, Integer roomId, Integer orderBy) {
        List<ProductConditionDTO> productConditionDTOList = new ArrayList<>();
        if(orderBy == 1){
            productConditionDTOList = productRepository.getProductWithCategoryIdAndRoomIdOrOrderByProductId(categoryId,roomId).stream().map(ProductConditionDTO::new).collect(Collectors.toList());
            return productConditionDTOList;
        }else if(orderBy == 2){
            productConditionDTOList = productRepository.getProductWithCategoryIdAndRoomIdOrOrderByNameAZ(categoryId,roomId).stream().map(ProductConditionDTO::new).collect(Collectors.toList());
            return productConditionDTOList;
        }else if(orderBy == 3){
            productConditionDTOList = productRepository.getProductWithCategoryIdAndRoomIdOrOrderByNameZA(categoryId,roomId).stream().map(ProductConditionDTO::new).collect(Collectors.toList());
            return productConditionDTOList;
        }else if(orderBy == 4){
            productConditionDTOList = productRepository.getProductWithCategoryIdAndRoomIdOrOrderByPriceLowToHigh(categoryId,roomId).stream().map(ProductConditionDTO::new).collect(Collectors.toList());
            return productConditionDTOList;
        }else{
            productConditionDTOList = productRepository.getProductWithCategoryIdAndRoomIdOrOrderByPriceHighToLow(categoryId,roomId).stream().map(ProductConditionDTO::new).collect(Collectors.toList());
            return productConditionDTOList;
        }
    }

    @Override
    public List<ProductConditionDTO> getProductWithConditionColor(String categoryId, Integer roomId, Integer colorId) {
        List<ProductConditionDTO> productConditionDTOList = productRepository.getProductWithCategoryIdAndRoomIdByColor(categoryId,roomId, colorId).stream().map(ProductConditionDTO::new).collect(Collectors.toList());
        return productConditionDTOList;
    }

    @Override
    public List<ProductConditionDTO> getProductWithConditionPrice(String categoryId, Integer roomId, Double minValue, Double maxValue) {
        List<ProductConditionDTO> productConditionDTOList = productRepository.getProductWithCategoryIdAndRoomIdByPrice(categoryId,roomId, minValue, maxValue).stream().map(ProductConditionDTO::new).collect(Collectors.toList());
        return productConditionDTOList;
    }

    @Override
    public List<ProductConditionDTO> getTop8NewProduct(Integer roomId) {
        List<ProductConditionDTO> productByRoomDTOList = productRepository.getTop8NewProduct(roomId).stream().map(ProductConditionDTO::new).collect(Collectors.toList());
        return productByRoomDTOList;
    }

    @Override
    public List<ProductConditionDTO> getTop8DiscountProduct(Integer roomId) {
        List<ProductConditionDTO> productByRoomDTOList = productRepository.getTop8DiscountProduct(roomId).stream().map(ProductConditionDTO::new).collect(Collectors.toList());
        return productByRoomDTOList;
    }

    @Override
    public ProductDTO getProductByProductId(String productId) {
        Product product = productRepository.getById(productId);
        ProductDTO productDTO = productMapper.toDTO(product);
        return productDTO;
    }

    @Override
    public ProductDTO updateProductByProductId(String productId, ProductRequest productRequest) {
        Product product = productRepository.findProductByProductId(productId);
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
    public List<ProductDTO> getAllProductBySearch(String productId, String productName, String categoryId, String providerId, Integer status) {
        if(productId != ""){
            List<Product> productList = productRepository.getAllByProductId(productId);
            List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
            return productDTOList;
        }else {
            if (productName != "" && categoryId == "" && providerId == "" && status == -1) {
                List<Product> productList = productRepository.findAllByProductName(productName);
                List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
                return productDTOList;
            }
            if (productName == "" && categoryId != "" && providerId == "" && status == -1) {
                Category category = categoryRepository.getById(categoryId);
                List<Product> productList = productRepository.findAllByCategory(category);
                List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
                return productDTOList;
            }
            if (productName == "" && categoryId == "" && providerId != "" && status == -1) {
                Provider provider = providerRepository.getById(providerId);
                List<Product> productList = productRepository.findAllByProvider(provider);
                List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
                return productDTOList;
            }
            if (productName == "" && categoryId == "" && providerId == "" && status != -1) {
                List<Product> productList = productRepository.findAllByStatus(status);
                List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
                return productDTOList;
            }
            if (productName != "" && categoryId != "" && providerId == "" && status == -1) {
                Category category = categoryRepository.getById(categoryId);
                List<Product> productList = productRepository.findAllByProductNameAndCategory(productName, category);
                List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
                return productDTOList;
            }
            if (productName != "" && categoryId == "" && providerId != "" && status == -1) {
                Provider provider = providerRepository.getById(providerId);
                List<Product> productList = productRepository.findAllByProductNameAndProvider(productName, provider);
                List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
                return productDTOList;
            }
            if (productName != "" && categoryId == "" && providerId == "" && status != -1) {
                List<Product> productList = productRepository.findAllByProductNameAndStatus(productName, status);
                List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
                return productDTOList;
            }
            if (productName == "" && categoryId != "" && providerId != "" && status == -1) {
                Category category = categoryRepository.getById(categoryId);
                Provider provider = providerRepository.getById(providerId);
                List<Product> productList = productRepository.findAllByCategoryAndProvider(category, provider);
                List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
                return productDTOList;
            }
            if (productName == "" && categoryId != "" && providerId == "" && status != -1) {
                Category category = categoryRepository.getById(categoryId);
                List<Product> productList = productRepository.findAllByCategoryAndStatus(category, status);
                List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
                return productDTOList;
            }
            if (productName == "" && categoryId == "" && providerId != "" && status != -1) {
                Provider provider = providerRepository.getById(providerId);
                List<Product> productList = productRepository.findAllByProviderAndStatus(provider, status);
                List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
                return productDTOList;
            }
            if (productName != "" && categoryId != "" && providerId != "" && status == -1) {
                Category category = categoryRepository.getById(categoryId);
                Provider provider = providerRepository.getById(providerId);
                List<Product> productList = productRepository.findAllByProductNameAndCategoryAndProvider(productName, category, provider);
                List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
                return productDTOList;
            }
            if (productName != "" && categoryId == "" && providerId != "" && status != -1) {
                Provider provider = providerRepository.getById(providerId);
                List<Product> productList = productRepository.findAllByProductNameAndProviderAndStatus(productName, provider, status);
                List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
                return productDTOList;
            }
            if (productName == "" && categoryId != "" && providerId != "" && status != -1) {
                Category category = categoryRepository.getById(categoryId);
                Provider provider = providerRepository.getById(providerId);
                List<Product> productList = productRepository.findAllByCategoryAndProviderAndStatus(category, provider, status);
                List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
                return productDTOList;
            }
            if (productName != "" && categoryId != "" && providerId != "" && status != -1) {
                Category category = categoryRepository.getById(categoryId);
                Provider provider = providerRepository.getById(providerId);
                List<Product> productList = productRepository.findAllByProductNameAndCategoryAndProviderAndStatus(productName, category, provider, status);
                List<ProductDTO> productDTOList = productList.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
                return productDTOList;
            }
        }
        List<ProductDTO> productDTOList = new ArrayList<>();
        return productDTOList;
    }

    @Override
    public Long newProductCount() {
        Long count = productRepository.countAllByStatus(1);
        return count;
    }
}
