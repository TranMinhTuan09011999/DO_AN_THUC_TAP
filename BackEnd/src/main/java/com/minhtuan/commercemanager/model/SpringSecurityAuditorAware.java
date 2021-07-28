package com.minhtuan.commercemanager.model;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor(){
        return Optional.ofNullable("MinhTuan").filter(s->!s.isEmpty());
    }
}
