package org.blitmatthew.userservice.controller;

import org.blitmatthew.userservice.dto.PostNewUserInformation;
import org.blitmatthew.userservice.dto.UpdateUserInformation;
import org.blitmatthew.userservice.entity.UserProfile;
import org.blitmatthew.userservice.service.UserProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userProfile")
public class UserProfileController {

    private UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping("/")
    public ResponseEntity<UserProfile> postNewUserProfile(
            @RequestBody PostNewUserInformation postNewUserInformation
    ) {
        return new ResponseEntity<>(userProfileService.createUserInformation(postNewUserInformation), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserProfile>> getAllProfile() {
        return ResponseEntity.ok(userProfileService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getUserProfile(@PathVariable Long id) {
        return ResponseEntity.ok(userProfileService.getUserById(id));
    }

    @PutMapping("/update")
    public ResponseEntity postUpdateUserProfile(@RequestBody UpdateUserInformation updateUserInformation) {
        userProfileService.updateUserInformation(updateUserInformation);
        return ResponseEntity.ok().build();
    }
}
