package org.blitmatthew.accountservice.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.blitmatthew.accountservice.annotation.ValidTransactionAction;
import org.blitmatthew.accountservice.dto.TransactionAction;

public class TransactionActionValidator implements ConstraintValidator<ValidTransactionAction, TransactionAction> {
    @Override
    public boolean isValid(TransactionAction transactionAction, ConstraintValidatorContext constraintValidatorContext) {
        if (transactionAction == null) {
            return false;
        }

        if (transactionAction.userProfileId() == null || transactionAction.userProfileId() <= 0) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("User profile ID must be positive and not null")
                    .addPropertyNode("userProfileId")
                    .addConstraintViolation();
            return false;
        }

        if (transactionAction.accountId() == null || transactionAction.accountId() <= 0) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Account ID must be positive and not null")
                    .addPropertyNode("accountId")
                    .addConstraintViolation();
            return false;
        }

        // Validate action (should be non-null and a valid AccountAction)
        if (transactionAction.action() == null) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Action must be specified")
                    .addPropertyNode("action")
                    .addConstraintViolation();
            return false;
        }

        // Validate amount (must be greater than zero)
        if (transactionAction.amount() == null || transactionAction.amount() <= 0) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Amount must be greater than zero")
                    .addPropertyNode("amount")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
