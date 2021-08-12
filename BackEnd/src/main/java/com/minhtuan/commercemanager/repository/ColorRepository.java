package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.Color;
import com.minhtuan.commercemanager.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color, Integer> {
    List<Color> findAllByOrderByColorId();
    Color findColorById(Integer Id);

    @Query(
            value = "SELECT ctsp.color.id, ctsp.color.colorId FROM ProductDetail ctsp " +
                    "WHERE ctsp.product.productId = :productId AND ctsp.size.sizeId = :sizeId AND ctsp.color.id <> :colorId "
    )
    List<Object[]> getColorByProductIdAndSizeId(@Param("productId") String productId, @Param("sizeId") Integer sizeId, @Param("colorId") Integer colorId);
}
