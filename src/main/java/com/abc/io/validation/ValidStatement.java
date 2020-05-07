package com.abc.io.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Duygu Muslu
 * @since  2020-05-06
 * @version 1.0
 */


@Constraint(validatedBy = {StatementValidator.class})
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStatement {
    String message() default "{EndBalace.Notsync}";

    Class[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}



