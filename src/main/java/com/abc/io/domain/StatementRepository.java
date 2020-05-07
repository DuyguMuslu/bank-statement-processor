package com.abc.io.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * StatementRepository.java is the repository for the CRUD operations on Statement
 * @see Statement
 *
 * @author Duygu Muslu
 * @since  2020-05-05
 * @version 1.0
 */
public interface StatementRepository extends CrudRepository<Statement, Long> {

    List<Statement> findAll();

    Statement findByReference(Long reference);
}

