package com.matome.accounts.repository;


import com.matome.accounts.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findAllByAccountNumber(BigDecimal accountNumber);

    @Query("From Bill a where a.billDate between :startDate AND  :endDate ")
    List<List> findAllByBillInDateRange(@Param("startDate")String startDate, @Param("startDate")String endDate);

    List<List> findAllByBillDateBetween(String startDate, String endDate);


}
