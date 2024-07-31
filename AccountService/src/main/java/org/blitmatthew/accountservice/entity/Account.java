package org.blitmatthew.accountservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.blitmatthew.accountservice.enums.AccountType;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private AccountType accountType = AccountType.CHECKING;
    private Double balance = 0.00;
    private Boolean isActive = true;
    private LocalDate createdAt = LocalDate.now();
    private LocalDate disabledAt;
    private Long userProfileId;
}
