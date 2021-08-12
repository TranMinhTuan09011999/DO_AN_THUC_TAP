package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.maper.WarehouseReceiptDetailMapper;
import com.minhtuan.commercemanager.message.request.WarehouseReceiptDetailRequest;
import com.minhtuan.commercemanager.message.response.WarehouseReceiptDetailResponse;
import com.minhtuan.commercemanager.message.response.WarehouseReceiptResponse;
import com.minhtuan.commercemanager.model.ProductDetail;
import com.minhtuan.commercemanager.model.WarehouseReceipt;
import com.minhtuan.commercemanager.model.WarehouseReceiptDetail;
import com.minhtuan.commercemanager.model.WarehouseReceiptDetailId;
import com.minhtuan.commercemanager.repository.ProductDetailRepository;
import com.minhtuan.commercemanager.repository.WarehouseReceiptDetailRepository;
import com.minhtuan.commercemanager.repository.WarehouseReceiptRepository;
import com.minhtuan.commercemanager.services.WarehouseReceiptDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseReceiptDetailServiceImpl implements WarehouseReceiptDetailService {

    @Autowired
    WarehouseReceiptRepository warehouseReceiptRepository;

    @Autowired
    ProductDetailRepository productDetailRepository;

    @Autowired
    WarehouseReceiptDetailRepository warehouseReceiptDetailRepository;

    @Autowired
    WarehouseReceiptDetailMapper warehouseReceiptDetailMapper;

    @Override
    public void save(WarehouseReceiptDetailRequest warehouseReceiptDetailRequest) {
        WarehouseReceiptDetail warehouseReceiptDetail = new WarehouseReceiptDetail();
        WarehouseReceipt warehouseReceipt = warehouseReceiptRepository.getById(warehouseReceiptDetailRequest.getWarehouseReceiptId());
        ProductDetail productDetail = productDetailRepository.findByProductDetailId(warehouseReceiptDetailRequest.getProductDetailId()).get();
        warehouseReceiptDetail.setWarehouseReceipt(warehouseReceipt);
        warehouseReceiptDetail.setProductDetail(productDetail);
        warehouseReceiptDetail.setQuantity(warehouseReceiptDetailRequest.getQuantity());
        warehouseReceiptDetail.setPrice(warehouseReceiptDetailRequest.getPrice());
        warehouseReceiptDetailRepository.save(warehouseReceiptDetail);
    }

    @Override
    public List<WarehouseReceiptDetailResponse> getAll(String warehouseReceiptId) {
        WarehouseReceipt warehouseReceipt = warehouseReceiptRepository.getById(warehouseReceiptId);
        List<WarehouseReceiptDetail> warehouseReceiptDetails = warehouseReceiptDetailRepository.findAllByWarehouseReceipt(warehouseReceipt);
        List<WarehouseReceiptDetailResponse> warehouseReceiptResponseList = warehouseReceiptDetails
                .stream()
                .map(q -> warehouseReceiptDetailMapper.toWarehouseReceiptDetailResponse(q))
                .collect(Collectors.toList());
        return warehouseReceiptResponseList;
    }
}
