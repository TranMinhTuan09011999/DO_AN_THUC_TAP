package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.dto.RoomDTO;
import com.minhtuan.commercemanager.dto.SizeDTO;
import com.minhtuan.commercemanager.model.Room;
import com.minhtuan.commercemanager.model.Size;
import org.springframework.stereotype.Component;

@Component
public class SizeMapper {
    public SizeDTO toDTO(Size size){
        SizeDTO dto = new SizeDTO();
        dto.setSizeId(size.getSizeId());
        dto.setSize(size.getSize());
        return dto;
    }
}
