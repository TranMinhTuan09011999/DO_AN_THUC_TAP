package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.Employee;
import com.minhtuan.commercemanager.model.Order;
import com.minhtuan.commercemanager.model.WarehouseReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WarehouseReceiptRepository extends JpaRepository<WarehouseReceipt, String> {
    List<WarehouseReceipt> findAll();
    List<WarehouseReceipt> findAllByWarehouseReceiptIdOrderByWarehouseReceiptIdDesc(String warehouseReceiptId);
    List<WarehouseReceipt> findAllByEmployeeOrderByWarehouseReceiptIdDesc(Employee employee);

    @Query(
            value = "SELECT w FROM WarehouseReceipt w " +
                    "WHERE w.dateOfIssue >= :fromDate AND w.dateOfIssue <= :toDate AND w.employee.id = :employeeId "+
                    "ORDER BY w.warehouseReceiptId DESC"
    )
    List<WarehouseReceipt> findWarehouseReceiptsByEmployeeIdOrderByWarehouseReceiptIdDescAndDate(@Param("employeeId") String employeeId, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    @Query(
            value = "SELECT w FROM WarehouseReceipt w " +
                    "WHERE w.dateOfIssue >= :fromDate AND w.dateOfIssue <= :toDate "+
                    "ORDER BY w.warehouseReceiptId DESC"
    )
    List<WarehouseReceipt> findWarehouseReceiptsOrderByWarehouseReceiptIdDescAndDate(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

    @Query(
            value = "SELECT SUM(pn.TONGTIEN) FROM phieunhap pn " +
                    "WHERE pn.NGAYNHAP >= :fromDate AND pn.NGAYNHAP <= :toDate "
            , nativeQuery = true
    )
    Double getProfitByTime(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}
