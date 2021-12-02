package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.Invoice;
import com.minhtuan.commercemanager.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String> {

    @Query(
            value = "SELECT SUM(hd.THANHTIEN) FROM hoadon hd " +
                    "WHERE hd.NGAY >= :fromDate AND hd.NGAY <= :toDate "
            , nativeQuery = true
    )
    Double getStaticByTime(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    @Query(
            value = "Select sp.TENSP, SUM(ctpd.SOLUONG) AS SOLUONG from ct_phieudat ctpd "
            + "inner join phieudat pd on ctpd.MAPD = pd.MAPD AND MONTH(pd.NGAYDAT) = :month AND YEAR(pd.NGAYDAT) = :year "
            + "inner join ct_sanpham ctsp on ctpd.MACTSP = ctsp.MACTSP "
            + "inner join sanpham sp on ctsp.MASP = sp.MASP "
            + "GROUP BY sp.MASP "
            + "ORDER BY sp.MASP ASC LIMIT 10 "
            , nativeQuery = true
    )
    List<Object[]> getHotSellingProduct(@Param("month") Integer month, @Param("year") Long year);

    @Query(
            value = "Select sp.TENSP, SUM(ctpd.SOLUONG) AS SOLUONG from ct_phieudat ctpd "
                    + "inner join phieudat pd on ctpd.MAPD = pd.MAPD AND pd.NGAYDAT >= :fromDate AND pd.NGAYDAT <= :toDate "
                    + "inner join ct_sanpham ctsp on ctpd.MACTSP = ctsp.MACTSP "
                    + "inner join sanpham sp on ctsp.MASP = sp.MASP "
                    + "GROUP BY sp.MASP "
                    + "ORDER BY sp.MASP ASC LIMIT 10 "
            , nativeQuery = true
    )
    List<Object[]> getHotSellingProductMonthFromTo(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}
