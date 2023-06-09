package com.isizwemadalane.investmentsapi.controller;

import com.isizwemadalane.investmentsapi.exception.ResourceNotFoundException;
import com.isizwemadalane.investmentsapi.model.*;
import com.isizwemadalane.investmentsapi.repository.InvestorRepository;
import com.isizwemadalane.investmentsapi.repository.ProductRepository;
import com.isizwemadalane.investmentsapi.repository.WithdrawalRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class WithdrawalController {

    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvestorRepository investorRepository;

    @Operation(summary = "This creates a new withdrawal for a selected product, only requires the withdrawalAmount")
    @PostMapping("/investors/{investorId}/products/{productId}/withdraw")
    public Withdrawal addWithdrawal(@PathVariable(value = "investorId") Long investorId, @PathVariable (value = "productId") Long productId, @RequestBody Withdrawal withdrawal) {
        Product product = productRepository.findByIdAndInvestorId(productId, investorId)
                .orElseThrow(() -> new ResourceNotFoundException("Product does not exist with id: " + productId + ", Investor does not exist with id: " + investorId));
        withdrawal.setWithdrawalStatus(WithdrawalStatus.STARTED);
        withdrawal.setProduct(product);
        return withdrawalRepository.save(withdrawal);
    }

    @Operation(summary = "This begins the withdrawal process, and creates a new withdrawal item")
    @PutMapping("/investors/{investorId}/products/{productId}/withdraw")
    public ResponseEntity<Product> initiateWithdrawal(@PathVariable(value = "investorId") Long investorId, @PathVariable(value = "productId") Long productId) {
        Investor investor = investorRepository.findById(investorId)
                .orElseThrow(() -> new ResourceNotFoundException("Investor does not exist with id: " + investorId));
        Product product = productRepository.findByIdAndInvestorId(productId, investorId)
                .orElseThrow(() -> new ResourceNotFoundException("Product does not exist with id: " + productId + ", Investor does not exist with id: " + investorId));

        product.setProductCurrentBalance(validate(investor, product));

        product.getWithdrawal().setWithdrawalAmount(0.0);
        product.getWithdrawal().setWithdrawalStatus(WithdrawalStatus.DONE);

        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    // TODO: Add security with roles to access the endpoints and make changes
    // TODO: Add security for the API
    private static Double validate(Investor investor, Product product) {
        // Check Age
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(investor.getInvestorDateOfBirth());

        if (product.getProductType() == ProductType.RETIREMENT && Period.between(birthDate, currentDate).getYears() < 65) {
            throw new IllegalStateException("The Investor is too young to withdraw from this product");
        }

        // Check Withdrawal Amount
        double withdrawalAmount = product.getWithdrawal().getWithdrawalAmount();
        double ninetyPercent = product.getProductCurrentBalance() * 0.9;

        if (withdrawalAmount > ninetyPercent) {
            throw new IllegalArgumentException("Withdrawal amount should not be more than ninety percent of the current balance");
        }

        double result = product.getProductCurrentBalance() - withdrawalAmount;
        return result;
    }
}
