package org.blitmatthew.userservice.dto;

import java.time.LocalDate;

public record UpdateUserInformation(
        long id,
        String firstName,
        String lastName,
        String address,
        long phone,
        Integer creditScore,
        LocalDate dateOfBirth
) {
}
