package org.blitmatthew.userservice.dto;

public record PostNewUserInformation(
        String firstName,
        String lastName,
        String address,
        long phone,
        Integer creditScore,
        String dateOfBirth,
        Long userCredentialId
) {
}
