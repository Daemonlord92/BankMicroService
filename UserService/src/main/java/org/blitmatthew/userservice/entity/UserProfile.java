package org.blitmatthew.userservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private Long phone;
    @Column(nullable = false)
    private Integer creditScore;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    private Long userCredentialId;
}
