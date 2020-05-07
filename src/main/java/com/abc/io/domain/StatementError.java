package com.abc.io.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
/**
 * StatementError.java is an entity for an error in record of an uploaded file.
 *
 * @author Duygu Muslu
 * @since  2020-05-06
 * @version 1.0
 */

@Data
@Entity
public class StatementError extends Auditable<String>{


    @Id
    @GeneratedValue
    private Long id;
    @Column(columnDefinition="TEXT")
    String message;
    Long reference;
    String accountNumber;
    BigDecimal startBalance;
    BigDecimal mutation;
    String description;
    BigDecimal endBalance;

    public StatementError(Statement s, String message) {
        this.accountNumber = s.accountNumber;
        this.description = s.description;
        this.endBalance = s.endBalance;
        this.startBalance = s.startBalance;
        this.mutation = s.mutation;
        this.reference = s.reference;
        this.message = message;
    }
}
