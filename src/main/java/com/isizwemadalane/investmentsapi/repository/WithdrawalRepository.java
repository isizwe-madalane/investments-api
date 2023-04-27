package com.isizwemadalane.investmentsapi.repository;

import com.isizwemadalane.investmentsapi.model.Withdrawal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
    Page<Withdrawal> findByProductId(Long productId, Pageable pageable);
}
