package com.minhtuan.commercemanager.services;

import com.minhtuan.commercemanager.dto.RoomDTO;
import com.minhtuan.commercemanager.dto.SizeDTO;
import com.minhtuan.commercemanager.message.response.SizeByProductIdResponse;
import com.minhtuan.commercemanager.model.Size;

import java.util.List;

public interface SizeService {

    public List<SizeDTO> getAllSize();
    public List<SizeByProductIdResponse> getSizeByProductId(String productId, Integer sizeId);
    public SizeDTO getSizeBySizeId(Integer sizeId);
}
