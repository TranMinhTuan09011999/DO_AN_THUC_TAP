package com.minhtuan.commercemanager.maper;

import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.dto.RoomDTO;
import com.minhtuan.commercemanager.model.Provider;
import com.minhtuan.commercemanager.model.Room;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {
    public RoomDTO toDTO(Room room){
        RoomDTO dto = new RoomDTO();
        dto.setRoomId(room.getRoomId());
        dto.setName(room.getName());
        return dto;
    }
}
