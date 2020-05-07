package com.abc.io.domain;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * StatementAuditListener is a listener for logging update and remove actions
 *
 * @author Duygu Muslu
 * @since  2020-05-06
 * @version 1.0
 */

@Slf4j
public class StatementAuditListener {

    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyUpdate(Statement statement) {
        if (statement.getId() == null) {
            log.info("[STATEMENT AUDIT] Persisting new statement");
        } else {
            log.info("[STATEMENT AUDIT] Updating a statement: " + statement.getId());
        }
    }

    @PostPersist
    @PostUpdate
    @PostRemove
    private void afterAnyUpdate(Statement statement) {
        log.info("[STATEMENT AUDIT] db operation completed for statement: " + statement.getId());
    }

    @PostLoad
    private void afterLoad(Statement statement) {
        log.info("[STATEMENT AUDIT] statement fetched from database: " + statement.getId());
    }
}
