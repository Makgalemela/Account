package com.matome.accounts.repository;


import com.matome.accounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountNumber(BigDecimal accountNumber);
    Account findByAccountHolderIDNumber(BigDecimal idNumber);

}
