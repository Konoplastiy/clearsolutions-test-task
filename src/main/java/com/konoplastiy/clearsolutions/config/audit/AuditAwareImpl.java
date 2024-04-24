package com.konoplastiy.clearsolutions.config.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Configuration class enabling JPA auditing features with a custom {@link AuditorAware} bean.
 *
 * @see AuditorAware
 */
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    /**
     * Returns the current auditor of the application.
     *
     * @return the current auditor.
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("ADMIN_MS");
    }

}