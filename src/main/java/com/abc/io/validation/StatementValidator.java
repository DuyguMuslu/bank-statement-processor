package com.abc.io.validation;

import com.abc.io.domain.Statement;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * StatementValidator is used for checking end balance value and uniqueness of the reference number
 *
 * @author Duygu Muslu
 * @since  2020-05-06
 * @version 1.0
 */

public class StatementValidator implements ConstraintValidator<ValidStatement, Statement> {
    @Override
    public boolean isValid(Statement statement, ConstraintValidatorContext constraintValidatorContext) {
        return statement.getEndBalance().equals(statement.getStartBalance().add(statement.getMutation()));
    }
}