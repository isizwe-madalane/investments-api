package com.isizwemadalane.investmentsapi.repository;

import com.isizwemadalane.investmentsapi.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByInvestorId(Long investorId, Pageable pageable);
    Optional<Product> findByIdAndInvestorId(Long id, Long investorId);
}
