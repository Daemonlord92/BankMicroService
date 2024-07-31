package org.blitmatthew.accountservice.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.blitmatthew.accountservice.validators.PostNewAccountValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PostNewAccountValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PostNewAccountValid {
    String message() default "Invalid account input";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
