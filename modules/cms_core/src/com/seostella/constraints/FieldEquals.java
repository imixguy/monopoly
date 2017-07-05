package com.seostella.constraints;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 26.11.13
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
import com.seostella.constraints.impl.FieldEqualsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = FieldEqualsValidator.class)
@Documented
public @interface FieldEquals {
    public static final String MESSAGE = "fields.notMatches";

    String message() default MESSAGE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target(TYPE)
    @Retention(RUNTIME)
    @Documented
    @interface List {
        FieldEquals[] value();
    }

    String field();

    String equalsTo();
}
