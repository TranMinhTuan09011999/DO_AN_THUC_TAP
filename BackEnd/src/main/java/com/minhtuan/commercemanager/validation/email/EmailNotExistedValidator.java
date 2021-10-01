package com.minhtuan.commercemanager.validation.email;

import com.minhtuan.commercemanager.model.Account;
import com.minhtuan.commercemanager.repository.AccountRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EmailNotExistedValidator {
    @Autowired
    private AccountRepository accountRepository;

    public boolean emailExists(String email) {
        boolean exists = true;

        if (email != null && !StringUtils.isBlank(email)) {
            Optional<Account> user = accountRepository.findByEmail(email);
            if(user.isPresent()) {
                exists = false;
            }
        }

        return exists;
    }
}
