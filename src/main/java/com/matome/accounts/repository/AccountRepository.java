package com.matome.accounts.repository;


import com.matome.accounts.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNumber(BigInteger accountNumber);
    Optional<Account> findByAccountHolderIDNumber(BigInteger idNumber);

    Boolean existsAccountByAccountNumber(BigInteger accountNumber);



}
