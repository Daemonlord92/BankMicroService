package org.blitmatthew.userservice.repository;

import org.blitmatthew.userservice.entity.UserProfile;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserProfileRepository extends ListCrudRepository<UserProfile, Long> {
    Optional<UserProfile> findByUserCredentialId(Long id);
}
