package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.ProductDetail;
import com.minhtuan.commercemanager.model.Provider;
import com.minhtuan.commercemanager.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Integer> {
    List<Size> findAllByOrderBySizeId();
    Size findSizeBySizeId(Integer sizeId);
}
