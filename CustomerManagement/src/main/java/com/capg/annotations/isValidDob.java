package com.capg.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(value = { ElementType.FIELD,ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DobValidator.class)
public @interface isValidDob {
    //error message
    public String message() default "Invalid date must be past date";
    //represents group of constraints
    public Class<?>[] groups() default {};
    //represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};
}