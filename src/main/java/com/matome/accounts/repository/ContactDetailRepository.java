package com.matome.accounts.repository;

import com.matome.accounts.model.ContactDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactDetailRepository extends JpaRepository<ContactDetail, Long> {
}
