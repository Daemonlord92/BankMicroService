package org.blitmatthew.accountservice.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.blitmatthew.accountservice.annotation.PostNewAccountValid;
import org.blitmatthew.accountservice.dto.PostNewAccount;

public class PostNewAccountValidator implements ConstraintValidator<PostNewAccountValid, PostNewAccount> {
    @Override
    public void initialize(PostNewAccountValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(PostNewAccount account, ConstraintValidatorContext constraintValidatorContext) {
        if(account == null) {
            return false;
        }

        String accountType = account.accountType();
        if (!"Checking".equalsIgnoreCase(accountType) && !"Saving".equalsIgnoreCase(accountType)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Account type must be either 'Checking' or 'Saving'")
                    .addConstraintViolation();
            return false;
        }

        if(account.accountType().equalsIgnoreCase("checking") ||
                account.accountType().equalsIgnoreCase("saving")
                && account.balance() <= 0) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Can not start an account with no balance")
                    .addConstraintViolation();
            return false;
        }

        if(account.userProfileId() <= 0) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("User profile id is required")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
