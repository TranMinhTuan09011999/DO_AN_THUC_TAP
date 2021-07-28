package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.Category;
import com.minhtuan.commercemanager.model.Product;
import com.minhtuan.commercemanager.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByOrderByProductIdDesc();
    Product findProductByProductId(String productId);
    Optional<Product> findProductsByProductId(String productId);
}
