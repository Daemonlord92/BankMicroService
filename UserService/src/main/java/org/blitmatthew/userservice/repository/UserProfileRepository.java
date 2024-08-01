package org.blitmatthew.userservice.repository;

import org.blitmatthew.userservice.entity.UserProfile;
import org.springframework.data.repository.ListCrudRepository;

public interface UserProfileRepository extends ListCrudRepository<UserProfile, Long> {
}
