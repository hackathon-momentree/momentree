package com.dteam.momentree.application.config.jpa;

import com.dteam.momentree.application.config.auth.UserContext;
import org.springframework.data.domain.AuditorAware;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(UserContext.getCurrentUser());
    }
}