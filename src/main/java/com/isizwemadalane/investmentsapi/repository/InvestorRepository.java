package com.isizwemadalane.investmentsapi.repository;

import com.isizwemadalane.investmentsapi.model.Investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestorRepository extends JpaRepository<Investor, Long> {
}
