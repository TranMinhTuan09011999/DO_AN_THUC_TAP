package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.Employee;
import com.minhtuan.commercemanager.model.Product;
import com.minhtuan.commercemanager.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    List<ProductDetail> findAllByOrderByProductDetailId();
    List<ProductDetail> findAllByProduct(Product product);
    Optional<ProductDetail> findByProductDetailId(Integer productDetailId);
}
