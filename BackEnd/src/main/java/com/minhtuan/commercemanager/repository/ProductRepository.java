package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.dto.ProductConditionDTO;
import com.minhtuan.commercemanager.model.Category;
import com.minhtuan.commercemanager.model.Product;
import com.minhtuan.commercemanager.model.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findAllByOrderByProductIdDesc();
    Product findProductByProductId(String productId);
    Optional<Product> findProductsByProductId(String productId);

    @Query(
            value = "SELECT sp.productId, sp.productName, ctsp.productDetailId, ctsp.image, ctsp.price, sp.description, ctsp.size.sizeId, ctsp.color.id, s.size, c.colorName, c.colorId FROM ProductDetail ctsp " +
                    "INNER JOIN Product sp ON ctsp.product.productId = sp.productId " +
                    "INNER JOIN Category dmh ON sp.category.categoryId = dmh.categoryId " +
                    "INNER JOIN Room p ON dmh.room.roomId = p.roomId " +
                    "INNER JOIN Size s ON ctsp.size.sizeId = s.sizeId " +
                    "INNER JOIN Color c ON ctsp.color.id = c.id " +
                    "WHERE dmh.categoryId = :categoryId AND p.roomId = :roomId AND sp.status = 1 " +
                    "GROUP BY sp.productId"
    )
    List<Object[]> getProductWithCategoryIdAndRoomId(@Param("categoryId") String categoryId, @Param("roomId") Integer roomId);

}
