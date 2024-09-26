package org.blitmatthew.userservice.exception;

import jakarta.persistence.EntityNotFoundException;

public class UserProfileNotFound extends EntityNotFoundException {
    public UserProfileNotFound(String msg) {
        super(msg);
    }
}
