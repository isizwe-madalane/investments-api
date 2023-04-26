package com.isizwemadalane.investmentsapi.repository;

import com.isizwemadalane.investmentsapi.model.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
}
