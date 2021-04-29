package com.matome.accounts.repository;

import com.matome.accounts.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByAccountNumber(BigDecimal accountNumber);
}
