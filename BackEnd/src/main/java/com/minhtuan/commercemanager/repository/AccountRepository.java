package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.Account;
import com.minhtuan.commercemanager.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByEmail(String email);
    Boolean existsByEmail(String email);
    Optional<Account> findByResetToken(String resetToken);

    @Query(
            value = "SELECT a FROM Account a " +
                    "WHERE a.created_date >= :fromDate AND a.created_date <= :toDate AND a.role.id = :roleId "
    )
    List<Account> findAccountsByStatusOrderByAndDate(@Param("roleId") Integer roleId, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

//    Boolean existsByUsername(String username);
//    Boolean existsByEmail(String email);
//    List<User> findAllByOrderByIdDesc();
//    Optional<User> findByResetToken(String resetToken);
}
