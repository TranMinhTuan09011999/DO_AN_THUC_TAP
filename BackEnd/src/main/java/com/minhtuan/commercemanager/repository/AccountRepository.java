package com.minhtuan.commercemanager.repository;

import com.minhtuan.commercemanager.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Optional<Account> findByEmail(String email);
    Boolean existsByEmail(String email);
//    Boolean existsByUsername(String username);
//    Boolean existsByEmail(String email);
//    List<User> findAllByOrderByIdDesc();
//    Optional<User> findByResetToken(String resetToken);
}