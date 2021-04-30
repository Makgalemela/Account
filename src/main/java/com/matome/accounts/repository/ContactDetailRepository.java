package com.matome.accounts.repository;

import com.matome.accounts.model.ContactDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;


@Repository
public interface ContactDetailRepository extends JpaRepository<ContactDetail, Long> {

    boolean existsByAccountNumber(BigInteger accountNUmber);

    ContactDetail findByAccountNumber(BigInteger accountNumber);
}
