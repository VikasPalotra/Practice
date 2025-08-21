package com.example.product_api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ProductCategoryValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)


public @interface ValidProductCategory {
    String message() default "Product category must be start with uppercase letter";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
