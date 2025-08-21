package com.example.product_api.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jdk.jfr.Category;

public class ProductCategoryValidator implements ConstraintValidator<ValidProductCategory,String> {

    @Override
    public boolean isValid(String category, ConstraintValidatorContext constraintValidatorContext) {
        return category!=null && Character.isUpperCase(category.charAt(0));
    }
}
