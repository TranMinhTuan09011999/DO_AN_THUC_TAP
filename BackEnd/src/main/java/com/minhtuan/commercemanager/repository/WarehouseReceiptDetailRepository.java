package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.WarehouseReceipt;
import com.minhtuan.commercemanager.model.WarehouseReceiptDetail;
import com.minhtuan.commercemanager.model.WarehouseReceiptDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseReceiptDetailRepository extends JpaRepository<WarehouseReceiptDetail, WarehouseReceiptDetailId> {
    List<WarehouseReceiptDetail> findAllByWarehouseReceipt(WarehouseReceipt warehouseReceipt);
}
