package org.blitmatthew.accountservice.repository;

import org.blitmatthew.accountservice.entity.Account;
import org.springframework.data.repository.ListCrudRepository;

public interface AccountRepository extends ListCrudRepository<Account, Long> {
}
