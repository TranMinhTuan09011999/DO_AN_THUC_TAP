package com.minhtuan.commercemanager.controller.admin;

import com.minhtuan.commercemanager.dto.ProviderDTO;
import com.minhtuan.commercemanager.dto.RoomDTO;
import com.minhtuan.commercemanager.services.ProviderService;
import com.minhtuan.commercemanager.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class RoomAdminController {

    @Autowired
    RoomService roomService;

    @GetMapping("/room")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<?> getRoom() {
        List<RoomDTO> roomDTOList = roomService.getAllRoom();
        return ResponseEntity.ok().body(roomDTOList);
    }
}
