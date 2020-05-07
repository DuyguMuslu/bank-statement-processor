package com.abc.io.domain;


import com.abc.io.validation.ValidStatement;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Statement.java represents a record of an uploaded file.
 *
 * @author Duygu Muslu
 * @since  2020-05-05
 * @version 1.0
 */

@EntityListeners(StatementAuditListener.class)
@Data
@Entity
@ValidStatement
public class Statement extends Auditable<String>{

    @Id
    @GeneratedValue
    private Long id;

    @NotNull(message = "NotNull.reference")
    @Column(unique = true)
    Long reference;

    String accountNumber;
    BigDecimal startBalance;
    BigDecimal mutation;
    String description;
    BigDecimal endBalance;
}

