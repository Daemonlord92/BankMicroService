package org.blitmatthew.userservice.service;

import org.blitmatthew.userservice.dto.PostNewUserInformation;
import org.blitmatthew.userservice.dto.UpdateUserInformation;
import org.blitmatthew.userservice.entity.UserProfile;
import org.blitmatthew.userservice.exception.UserProfileNotFound;
import org.blitmatthew.userservice.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Override
    public UserProfile createUserInformation(PostNewUserInformation request) {
        UserProfile userProfile = UserProfile.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .phone(request.phone())
                .address(request.address())
                .dateOfBirth(LocalDate.parse(request.dateOfBirth()))
                .creditScore(request.creditScore())
                .userCredentialId(request.userCredentialId())
                .build();
        return userProfileRepository.save(userProfile);
    }

    @Override
    public void updateUserInformation(UpdateUserInformation request) {
        UserProfile userProfile = userProfileRepository.findById(request.id()).orElseThrow(
                () -> new UserProfileNotFound("User Profile with id of " + request.id() + " Not found"));
        userProfile.setAddress(request.address());
        userProfile.setCreditScore(request.creditScore());
        userProfile.setFirstName(request.firstName());
        userProfile.setLastName(request.lastName());
        userProfile.setPhone(request.phone());
        userProfile.setDateOfBirth(request.dateOfBirth());
        userProfileRepository.save(userProfile);
    }

    @Override
    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile getUserById(Long id) {
        return userProfileRepository.findById(id).orElseThrow(
                () -> new UserProfileNotFound("User Profile with id of " + id + " Not found"));
    }
}
