package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Integer> {
    List<ProductDetail> findAllByOrderByProductDetailId();
    List<ProductDetail> findAllByProduct(Product product);
    Optional<ProductDetail> findByProductDetailId(Integer productDetailId);

    @Query(
            value = "SELECT sp.productId, sp.productName, ctsp.productDetailId, ctsp.price, sp.description, ctsp.size.sizeId, ctsp.color.id, s.size, c.colorName, c.colorId, ctsp.discount, sp.status, ctsp.quantity, r.name, ca.categoryName FROM ProductDetail ctsp " +
                    "INNER JOIN Product sp ON ctsp.product.productId = sp.productId " +
                    "INNER JOIN Category ca ON sp.category.categoryId = ca.categoryId " +
                    "INNER JOIN Room r ON ca.room.roomId = r.roomId " +
                    "INNER JOIN Size s ON ctsp.size.sizeId = s.sizeId " +
                    "INNER JOIN Color c ON ctsp.color.id = c.id " +
                    "WHERE ctsp.product.productId = :productId AND ctsp.color.id = :colorId AND ctsp.size.sizeId = :sizeId AND (sp.status = 1 OR sp.status = 2)"
    )
    List<Object[]> getProductDetailWithProductIdAndColorIdAndSizeId(@Param("productId") String productId, @Param("colorId") Integer colorId, @Param("sizeId") Integer sizeId);

    List<ProductDetail> findFirstBySize(Size size);

    @Query(
            value = "SELECT ctsp.MACTSP, sp.MASP, sp.TENSP, ctsp.GIA, s.MAKT, s.TENKT, c.ID, c.TENMAU, ctsp.KHUYENMAI FROM ct_sanpham ctsp " +
                    "INNER JOIN sanpham sp ON ctsp.MASP = sp.MASP " +
                    "INNER JOIN kichthuoc s ON ctsp.MAKT = s.MAKT " +
                    "INNER JOIN mau c ON ctsp.MAMAU = c.ID " +
                    "WHERE ctsp.MACTSP = :productDetailId"
            , nativeQuery = true
    )
    List<Object[]> getProductDetailWithProductDetailId(@Param("productDetailId") Integer productDetailId);
    ProductDetail findProductDetailByProductAndSizeAndColor(Product product, Size size, Color color);
    List<ProductDetail> findAllBySizeAndProductOrderByProductDetailIdDesc(Size size, Product product);
    List<ProductDetail> findAllByColorAndProductOrderByProductDetailIdDesc(Color color, Product product);
    List<ProductDetail> findAllBySizeAndColorAndProductOrderByProductDetailIdDesc( Size size, Color color, Product product);
}
