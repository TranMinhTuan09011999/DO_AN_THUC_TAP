package com.minhtuan.commercemanager.services.ServicesImpl;

import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.dto.RoomDTO;
import com.minhtuan.commercemanager.maper.RoomMapper;
import com.minhtuan.commercemanager.model.Room;
import com.minhtuan.commercemanager.repository.ProviderRepository;
import com.minhtuan.commercemanager.repository.RoomRepository;
import com.minhtuan.commercemanager.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<RoomDTO> getAllRoom() {
        List<Room> roomList = roomRepository.findAll();
        List<RoomDTO> roomDTOList = roomList.stream().map(room -> roomMapper.toDTO(room)).collect(Collectors.toList());
        return roomDTOList;
    }
}
