package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.dto.ProductConditionDTO;
import com.minhtuan.commercemanager.model.Category;
import com.minhtuan.commercemanager.model.Product;
import com.minhtuan.commercemanager.model.Provider;
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
            value = "SELECT sp.MASP, sp.TENSP, ctsp.MACTSP, ctsp.GIA, sp.MOTA, kt.MAKT, m.ID, kt.TENKT, m.TENMAU, m.MAMAU, ctsp.KHUYENMAI, sp.TRANGTHAI, ctsp.SOLUONG, sp.TENPHONG, sp.TENDMH FROM ( " +
                    "   SELECT sp.MASP, sp.TENSP, sp.MOTA, sp.TRANGTHAI, p.TENPHONG, dmh.TENDMH FROM sanpham sp " +
                    "   INNER JOIN danhmuchang dmh on sp.MADMH = dmh.MADMH " +
                    "   INNER JOIN phong p on dmh.MAPHONG = p.MAPHONG " +
                    "   WHERE p.MAPHONG = :roomId AND dmh.MADMH = :categoryId AND (sp.TRANGTHAI = 1 OR sp.TRANGTHAI = 2)" +
                    ") sp " +
                    "INNER JOIN ct_sanpham ctsp ON sp.MASP = ctsp.MASP " +
                    "INNER JOIN kichthuoc kt ON ctsp.MAKT = kt.MAKT " +
                    "INNER JOIN mau m ON ctsp.MAMAU = m.ID " +
                    "INNER JOIN ( " +
                    "   SELECT sp.MASP, ctsp.MACTSP, MAX(ctsp.KHUYENMAI) AS KHUYENMAI FROM sanpham sp " +
                    "   INNER JOIN ct_sanpham ctsp on sp.MASP = ctsp.MASP " +
                    "   GROUP BY sp.MASP " +
                    ") y ON ctsp.MASP = y.MASP AND ctsp.KHUYENMAI = y.KHUYENMAI " +
                    "GROUP BY sp.MASP " +
                    "ORDER BY sp.MASP DESC"
            , nativeQuery = true
    )
    List<Object[]> getProductWithCategoryIdAndRoomIdOrOrderByProductId(@Param("categoryId") String categoryId, @Param("roomId") Integer roomId);

    @Query(
            value = "SELECT sp.MASP, sp.TENSP, ctsp.MACTSP, ctsp.GIA, sp.MOTA, kt.MAKT, m.ID, kt.TENKT, m.TENMAU, m.MAMAU, ctsp.KHUYENMAI, sp.TRANGTHAI, ctsp.SOLUONG, sp.TENPHONG, sp.TENDMH FROM ( " +
                    "   SELECT sp.MASP, sp.TENSP, sp.MOTA, sp.TRANGTHAI, p.TENPHONG, dmh.TENDMH FROM sanpham sp " +
                    "   INNER JOIN danhmuchang dmh on sp.MADMH = dmh.MADMH " +
                    "   INNER JOIN phong p on dmh.MAPHONG = p.MAPHONG " +
                    "   WHERE p.MAPHONG = :roomId AND dmh.MADMH = :categoryId AND (sp.TRANGTHAI = 1 OR sp.TRANGTHAI = 2)" +
                    ") sp " +
                    "INNER JOIN ct_sanpham ctsp ON sp.MASP = ctsp.MASP " +
                    "INNER JOIN kichthuoc kt ON ctsp.MAKT = kt.MAKT " +
                    "INNER JOIN mau m ON ctsp.MAMAU = m.ID " +
                    "INNER JOIN ( " +
                    "   SELECT sp.MASP, ctsp.MACTSP, MAX(ctsp.KHUYENMAI) AS KHUYENMAI FROM sanpham sp " +
                    "   INNER JOIN ct_sanpham ctsp on sp.MASP = ctsp.MASP " +
                    "   GROUP BY sp.MASP " +
                    ") y ON ctsp.MASP = y.MASP AND ctsp.KHUYENMAI = y.KHUYENMAI " +
                    "GROUP BY sp.MASP " +
                    "ORDER BY sp.TENSP ASC"
            , nativeQuery = true
    )
    List<Object[]> getProductWithCategoryIdAndRoomIdOrOrderByNameAZ(@Param("categoryId") String categoryId, @Param("roomId") Integer roomId);

    @Query(
            value = "SELECT sp.MASP, sp.TENSP, ctsp.MACTSP, ctsp.GIA, sp.MOTA, kt.MAKT, m.ID, kt.TENKT, m.TENMAU, m.MAMAU, ctsp.KHUYENMAI, sp.TRANGTHAI, ctsp.SOLUONG, sp.TENPHONG, sp.TENDMH FROM ( " +
                    "   SELECT sp.MASP, sp.TENSP, sp.MOTA, sp.TRANGTHAI, p.TENPHONG, dmh.TENDMH FROM sanpham sp " +
                    "   INNER JOIN danhmuchang dmh on sp.MADMH = dmh.MADMH " +
                    "   INNER JOIN phong p on dmh.MAPHONG = p.MAPHONG " +
                    "   WHERE p.MAPHONG = :roomId AND dmh.MADMH = :categoryId AND (sp.TRANGTHAI = 1 OR sp.TRANGTHAI = 2)" +
                    ") sp " +
                    "INNER JOIN ct_sanpham ctsp ON sp.MASP = ctsp.MASP " +
                    "INNER JOIN kichthuoc kt ON ctsp.MAKT = kt.MAKT " +
                    "INNER JOIN mau m ON ctsp.MAMAU = m.ID " +
                    "INNER JOIN ( " +
                    "   SELECT sp.MASP, ctsp.MACTSP, MAX(ctsp.KHUYENMAI) AS KHUYENMAI FROM sanpham sp " +
                    "   INNER JOIN ct_sanpham ctsp on sp.MASP = ctsp.MASP " +
                    "   GROUP BY sp.MASP " +
                    ") y ON ctsp.MASP = y.MASP AND ctsp.KHUYENMAI = y.KHUYENMAI " +
                    "GROUP BY sp.MASP " +
                    "ORDER BY sp.TENSP DESC"
            , nativeQuery = true
    )
    List<Object[]> getProductWithCategoryIdAndRoomIdOrOrderByNameZA(@Param("categoryId") String categoryId, @Param("roomId") Integer roomId);

    @Query(
            value = "SELECT sp.MASP, sp.TENSP, ctsp.MACTSP, ctsp.GIA, sp.MOTA, kt.MAKT, m.ID, kt.TENKT, m.TENMAU, m.MAMAU, ctsp.KHUYENMAI, sp.TRANGTHAI, ctsp.SOLUONG, sp.TENPHONG, sp.TENDMH FROM ( " +
                    "   SELECT sp.MASP, sp.TENSP, sp.MOTA, sp.TRANGTHAI, p.TENPHONG, dmh.TENDMH FROM sanpham sp " +
                    "   INNER JOIN danhmuchang dmh on sp.MADMH = dmh.MADMH " +
                    "   INNER JOIN phong p on dmh.MAPHONG = p.MAPHONG " +
                    "   WHERE p.MAPHONG = :roomId AND dmh.MADMH = :categoryId AND (sp.TRANGTHAI = 1 OR sp.TRANGTHAI = 2)" +
                    ") sp " +
                    "INNER JOIN ct_sanpham ctsp ON sp.MASP = ctsp.MASP " +
                    "INNER JOIN kichthuoc kt ON ctsp.MAKT = kt.MAKT " +
                    "INNER JOIN mau m ON ctsp.MAMAU = m.ID " +
                    "INNER JOIN ( " +
                    "   SELECT sp.MASP, ctsp.MACTSP, MAX(ctsp.KHUYENMAI) AS KHUYENMAI FROM sanpham sp " +
                    "   INNER JOIN ct_sanpham ctsp on sp.MASP = ctsp.MASP " +
                    "   GROUP BY sp.MASP " +
                    ") y ON ctsp.MASP = y.MASP AND ctsp.KHUYENMAI = y.KHUYENMAI " +
                    "GROUP BY sp.MASP " +
                    "ORDER BY ctsp.GIA ASC"
            , nativeQuery = true
    )
    List<Object[]> getProductWithCategoryIdAndRoomIdOrOrderByPriceLowToHigh(@Param("categoryId") String categoryId, @Param("roomId") Integer roomId);

    @Query(
            value = "SELECT sp.MASP, sp.TENSP, ctsp.MACTSP, ctsp.GIA, sp.MOTA, kt.MAKT, m.ID, kt.TENKT, m.TENMAU, m.MAMAU, ctsp.KHUYENMAI, sp.TRANGTHAI, ctsp.SOLUONG, sp.TENPHONG, sp.TENDMH FROM ( " +
                    "   SELECT sp.MASP, sp.TENSP, sp.MOTA, sp.TRANGTHAI, p.TENPHONG, dmh.TENDMH FROM sanpham sp " +
                    "   INNER JOIN danhmuchang dmh on sp.MADMH = dmh.MADMH " +
                    "   INNER JOIN phong p on dmh.MAPHONG = p.MAPHONG " +
                    "   WHERE p.MAPHONG = :roomId AND dmh.MADMH = :categoryId AND (sp.TRANGTHAI = 1 OR sp.TRANGTHAI = 2)" +
                    ") sp " +
                    "INNER JOIN ct_sanpham ctsp ON sp.MASP = ctsp.MASP " +
                    "INNER JOIN kichthuoc kt ON ctsp.MAKT = kt.MAKT " +
                    "INNER JOIN mau m ON ctsp.MAMAU = m.ID " +
                    "INNER JOIN ( " +
                    "   SELECT sp.MASP, ctsp.MACTSP, MAX(ctsp.KHUYENMAI) AS KHUYENMAI FROM sanpham sp " +
                    "   INNER JOIN ct_sanpham ctsp on sp.MASP = ctsp.MASP " +
                    "   GROUP BY sp.MASP " +
                    ") y ON ctsp.MASP = y.MASP AND ctsp.KHUYENMAI = y.KHUYENMAI " +
                    "GROUP BY sp.MASP " +
                    "ORDER BY ctsp.GIA DESC"
            , nativeQuery = true
    )
    List<Object[]> getProductWithCategoryIdAndRoomIdOrOrderByPriceHighToLow(@Param("categoryId") String categoryId, @Param("roomId") Integer roomId);

    @Query(
            value = "SELECT sp.MASP, sp.TENSP, ctsp.MACTSP, ctsp.GIA, sp.MOTA, kt.MAKT, m.ID, kt.TENKT, m.TENMAU, m.MAMAU, ctsp.KHUYENMAI, sp.TRANGTHAI, ctsp.SOLUONG, sp.TENPHONG, sp.TENDMH  FROM ( " +
                    "   SELECT sp.MASP, sp.TENSP, sp.MOTA, sp.TRANGTHAI, p.TENPHONG, dmh.TENDMH FROM sanpham sp " +
                    "   INNER JOIN danhmuchang dmh on sp.MADMH = dmh.MADMH " +
                    "   INNER JOIN phong p on dmh.MAPHONG = p.MAPHONG " +
                    "   WHERE p.MAPHONG = :roomId AND dmh.MADMH = :categoryId AND (sp.TRANGTHAI = 1 OR sp.TRANGTHAI = 2)" +
                    ") sp " +
                    "INNER JOIN ct_sanpham ctsp ON sp.MASP = ctsp.MASP " +
                    "INNER JOIN kichthuoc kt ON ctsp.MAKT = kt.MAKT " +
                    "INNER JOIN (SELECT m.ID, m.TENMAU, m.MAMAU FROM mau m WHERE m.ID = :colorId) m ON ctsp.MAMAU = m.ID " +
                    "INNER JOIN ( " +
                    "   SELECT sp.MASP, ctsp.MACTSP, MAX(ctsp.KHUYENMAI) AS KHUYENMAI FROM sanpham sp " +
                    "   INNER JOIN ct_sanpham ctsp on sp.MASP = ctsp.MASP " +
                    "   GROUP BY sp.MASP " +
                    ") y ON ctsp.MASP = y.MASP AND ctsp.KHUYENMAI = y.KHUYENMAI " +
                    "GROUP BY sp.MASP"
            , nativeQuery = true
    )
    List<Object[]> getProductWithCategoryIdAndRoomIdByColor(@Param("categoryId") String categoryId, @Param("roomId") Integer roomId, @Param("colorId") Integer colorId);

    @Query(
            value = "SELECT sp.MASP, sp.TENSP, ctsp.MACTSP, ctsp.GIA, sp.MOTA, kt.MAKT, m.ID, kt.TENKT, m.TENMAU, m.MAMAU, ctsp.KHUYENMAI, sp.TRANGTHAI, ctsp.SOLUONG, sp.TENPHONG, sp.TENDMH  FROM ( " +
                    "   SELECT sp.MASP, sp.TENSP, sp.MOTA, sp.TRANGTHAI, p.TENPHONG, dmh.TENDMH FROM sanpham sp " +
                    "   INNER JOIN danhmuchang dmh on sp.MADMH = dmh.MADMH " +
                    "   INNER JOIN phong p on dmh.MAPHONG = p.MAPHONG " +
                    "   WHERE p.MAPHONG = :roomId AND dmh.MADMH = :categoryId AND (sp.TRANGTHAI = 1 OR sp.TRANGTHAI = 2)" +
                    ") sp " +
                    "INNER JOIN (SELECT ctsp.MAMAU, ctsp.MAKT, ctsp.MASP, ctsp.MACTSP, ctsp.GIA, ctsp.KHUYENMAI, ctsp.SOLUONG FROM ct_sanpham ctsp WHERE ctsp.GIA >= :minValue AND ctsp.GIA <= :maxValue) ctsp ON sp.MASP = ctsp.MASP " +
                    "INNER JOIN kichthuoc kt ON ctsp.MAKT = kt.MAKT " +
                    "INNER JOIN mau m ON ctsp.MAMAU = m.ID " +
                    "INNER JOIN ( " +
                    "   SELECT sp.MASP, ctsp.MACTSP, MAX(ctsp.KHUYENMAI) AS KHUYENMAI FROM sanpham sp " +
                    "   INNER JOIN ct_sanpham ctsp on sp.MASP = ctsp.MASP " +
                    "   GROUP BY sp.MASP " +
                    ") y ON ctsp.MASP = y.MASP AND ctsp.KHUYENMAI = y.KHUYENMAI " +
                    "GROUP BY sp.MASP"
            , nativeQuery = true
    )
    List<Object[]> getProductWithCategoryIdAndRoomIdByPrice(@Param("categoryId") String categoryId, @Param("roomId") Integer roomId, @Param("minValue") Double minValue, @Param("maxValue") Double maxValue);

    @Query(
            value = "SELECT sp.MASP, sp.TENSP, ctsp.MACTSP, ctsp.GIA, sp.MOTA, kt.MAKT, m.ID, kt.TENKT, m.TENMAU, m.MAMAU, ctsp.KHUYENMAI, sp.TRANGTHAI, ctsp.SOLUONG, sp.TENPHONG, sp.TENDMH FROM ( " +
                    "   SELECT sp.MASP, sp.TENSP, sp.MOTA, sp.TRANGTHAI, p.TENPHONG, dmh.TENDMH FROM sanpham sp " +
                    "   INNER JOIN danhmuchang dmh on sp.MADMH = dmh.MADMH " +
                    "   INNER JOIN phong p on dmh.MAPHONG = p.MAPHONG " +
                    "   WHERE p.MAPHONG = :roomId AND sp.TRANGTHAI = 1" +
                    ") sp " +
                    "INNER JOIN ct_sanpham ctsp ON sp.MASP = ctsp.MASP " +
                    "INNER JOIN kichthuoc kt ON ctsp.MAKT = kt.MAKT " +
                    "INNER JOIN mau m ON ctsp.MAMAU = m.ID " +
                    "INNER JOIN ( " +
                    "   SELECT sp.MASP, ctsp.MACTSP, MAX(ctsp.KHUYENMAI) AS KHUYENMAI FROM sanpham sp " +
                    "   INNER JOIN ct_sanpham ctsp on sp.MASP = ctsp.MASP " +
                    "   GROUP BY sp.MASP " +
                    ") y ON ctsp.MASP = y.MASP AND ctsp.KHUYENMAI = y.KHUYENMAI " +
                    "GROUP BY sp.MASP " +
                    "ORDER BY sp.MASP DESC LIMIT 6"
            , nativeQuery = true
    )
    List<Object[]> getTop8NewProduct(@Param("roomId") Integer roomId);

    @Query(
            value = "SELECT sp.MASP, sp.TENSP, ctsp.MACTSP, ctsp.GIA, sp.MOTA, kt.MAKT, m.ID, kt.TENKT, m.TENMAU, m.MAMAU, ctsp.KHUYENMAI, sp.TRANGTHAI, ctsp.SOLUONG, sp.TENPHONG, sp.TENDMH FROM ( " +
                    "   SELECT sp.MASP, sp.TENSP, sp.MOTA, sp.TRANGTHAI, p.TENPHONG, dmh.TENDMH FROM sanpham sp " +
                    "   INNER JOIN danhmuchang dmh on sp.MADMH = dmh.MADMH " +
                    "   INNER JOIN phong p on dmh.MAPHONG = p.MAPHONG " +
                    "   WHERE p.MAPHONG = :roomId AND (sp.TRANGTHAI = 1 OR sp.TRANGTHAI = 2)" +
                    ") sp " +
                    "INNER JOIN ct_sanpham ctsp ON sp.MASP = ctsp.MASP " +
                    "INNER JOIN kichthuoc kt ON ctsp.MAKT = kt.MAKT " +
                    "INNER JOIN mau m ON ctsp.MAMAU = m.ID " +
                    "INNER JOIN ( " +
                    "   SELECT sp.MASP, ctsp.MACTSP, MAX(ctsp.KHUYENMAI) AS KHUYENMAI FROM sanpham sp " +
                    "   INNER JOIN ct_sanpham ctsp on sp.MASP = ctsp.MASP " +
                    "   GROUP BY sp.MASP " +
                    ") y ON ctsp.MASP = y.MASP AND ctsp.KHUYENMAI = y.KHUYENMAI " +
                    "WHERE ctsp.KHUYENMAI > 0 " +
                    "GROUP BY sp.MASP " +
                    "ORDER BY sp.MASP DESC LIMIT 6"
            , nativeQuery = true
    )
    List<Object[]> getTop8DiscountProduct(@Param("roomId") Integer roomId);

    List<Product> getAllByProductId(String propuctId);
    List<Product> findAllByProductName(String productName);
    List<Product> findAllByCategory(Category category);
    List<Product> findAllByProvider(Provider provider);
    List<Product> findAllByStatus(Integer status);
    List<Product> findAllByProductNameAndCategory(String propuctId, Category category);
    List<Product> findAllByProductNameAndProvider(String propuctId, Provider provider);
    List<Product> findAllByProductNameAndStatus(String propuctId, Integer status);
    List<Product> findAllByCategoryAndProvider(Category category, Provider provider);
    List<Product> findAllByCategoryAndStatus(Category category, Integer status);
    List<Product> findAllByProviderAndStatus(Provider provider, Integer status);
    List<Product> findAllByProductNameAndCategoryAndProvider(String productName,Category category, Provider provider);
    List<Product> findAllByProductNameAndProviderAndStatus(String productName, Provider provider, Integer status);
    List<Product> findAllByCategoryAndProviderAndStatus(Category category, Provider provider, Integer status);
    List<Product> findAllByProductNameAndCategoryAndProviderAndStatus(String productName, Category category, Provider provider, Integer status);

    Long countAllByStatus(Integer status);
}
