package com.matome.accounts.repository;

import com.matome.accounts.model.Address;
import com.matome.accounts.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Boolean existsByAccountNumber(BigInteger accountNumber);

    Address findByAccountNumber(BigInteger accountNumber);
}
