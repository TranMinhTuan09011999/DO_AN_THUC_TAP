package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.Customer;
import com.minhtuan.commercemanager.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    Room findRoomByRoomId(Integer roomId);
}
