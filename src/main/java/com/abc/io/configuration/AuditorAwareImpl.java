package com.abc.io.configuration;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
/**
 * AuditorAwareImpl is used for getting auditor info for Admin user
 * @author Duygu Muslu
 * @since  2020-05-06
 * @version 1.0
 */
 
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor(){
        return Optional.of("Admin");
    }
}
