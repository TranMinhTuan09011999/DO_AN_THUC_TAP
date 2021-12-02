package com.minhtuan.commercemanager.controller.customer;

import com.minhtuan.commercemanager.dto.RoomDTO;
import com.minhtuan.commercemanager.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/customer")
public class RoomController {

    @Autowired
    RoomService roomService;

    @GetMapping("/room")
    public ResponseEntity<?> getRoom() {
        List<RoomDTO> roomDTOList = roomService.getAllRoom();
        return ResponseEntity.ok().body(roomDTOList);
    }
}
