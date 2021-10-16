package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.dto.ChatDTO;
import com.minhtuan.commercemanager.model.Chat;
import com.minhtuan.commercemanager.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
    List<Chat> findAllByCustomerChatOrCustomerToChat(Customer userId, Customer toUserId);

    @Query(
            value = "select c.user_id from chat c\n" +
                    "where c.user_id != 'null'\n" +
                    "group by c.user_id"
            , nativeQuery = true
    )
    List<String> getUserList();

    @Query(
            value = "Select c.* from chat c where (c.user_id = :userId or c.to_user_id = :userId) and c.time = (\n" +
                    "\tselect MAX(c.time) from chat c where c.user_id = :userId or c.to_user_id = :userId\n" +
                    ")"
            , nativeQuery = true
    )
    Chat getMessageByUser(@Param("userId") String userId);
}
