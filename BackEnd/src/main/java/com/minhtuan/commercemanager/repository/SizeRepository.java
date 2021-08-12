package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.ProductDetail;
import com.minhtuan.commercemanager.model.Provider;
import com.minhtuan.commercemanager.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SizeRepository extends JpaRepository<Size, Integer> {
    List<Size> findAllByOrderBySizeId();
    Size findSizeBySizeId(Integer sizeId);

    @Query(
            value = "SELECT ctsp.size.sizeId, s.size, ctsp.color.id FROM ProductDetail ctsp " +
                    "INNER JOIN Size s ON ctsp.size.sizeId = s.sizeId " +
                    "WHERE ctsp.product.productId = :productId AND ctsp.size.sizeId <> :sizeId " +
                    "GROUP BY ctsp.size.sizeId"
    )
    List<Object[]> getSizeByProductId(@Param("productId") String productId, @Param("sizeId") Integer sizeId);
}
