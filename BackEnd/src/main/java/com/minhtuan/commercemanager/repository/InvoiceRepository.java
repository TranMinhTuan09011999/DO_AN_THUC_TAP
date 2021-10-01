package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.Invoice;
import com.minhtuan.commercemanager.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, String> {
    @Query(
            value = "SELECT SUM(hd.THANHTIEN) FROM hoadon hd " +
                    "WHERE MONTH(CAST(hd.NGAY as DATE)) = :month " +
                    "AND YEAR(CAST(hd.NGAY as DATE)) = :year"
            , nativeQuery = true
    )
    Double getAmountByMonth(@Param("month") Integer month, @Param("year") Integer year);
}
