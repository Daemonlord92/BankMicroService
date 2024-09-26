package org.blitmatthew.userservice.service;

import org.blitmatthew.userservice.dto.PostNewUserInformation;
import org.blitmatthew.userservice.dto.UpdateUserInformation;
import org.blitmatthew.userservice.entity.UserProfile;

import java.util.List;

public interface UserProfileService {
    UserProfile createUserInformation(PostNewUserInformation request);
    void updateUserInformation(UpdateUserInformation request);
    List<UserProfile> getAllUsers();
    UserProfile getUserById(Long id);
}
