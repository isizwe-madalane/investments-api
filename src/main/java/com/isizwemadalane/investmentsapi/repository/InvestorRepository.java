package com.isizwemadalane.investmentsapi.repository;

import com.isizwemadalane.investmentsapi.models.Investor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvestorRepository extends JpaRepository<Investor, Long> {
}
