package com.isizwemadalane.investmentsapi.controller;

import com.isizwemadalane.investmentsapi.exception.ResourceNotFoundException;
import com.isizwemadalane.investmentsapi.model.*;
import com.isizwemadalane.investmentsapi.repository.InvestorRepository;
import com.isizwemadalane.investmentsapi.repository.ProductRepository;
import com.isizwemadalane.investmentsapi.repository.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvestorRepository investorRepository;

    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @GetMapping("/investors/{investorId}/products")
    public Page<Product> getAllProductsByInvestorId(@PathVariable (value = "investorId") Long investorId, Pageable pageable) {
        return productRepository.findByInvestorId(investorId, pageable);
    }

    @GetMapping("/investors/{investorId}/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable (value = "investorId") Long investorId, @PathVariable (value = "productId") Long productId) {
        Product product = productRepository.findByIdAndInvestorId(productId, investorId)
                .orElseThrow(() -> new ResourceNotFoundException("Product does not exist with id: " + productId + ", Investor does not exist with id: " + investorId));
        return ResponseEntity.ok(product);
    }

    @PostMapping("/investors/{investorId}/products")
    public Product addProduct(@PathVariable (value = "investorId") Long investorId, @RequestBody Product product) {
        Investor investor = investorRepository.findById(investorId)
                .orElseThrow(() -> new ResourceNotFoundException("Investor does not exist with id: " + investorId));

        product.setWithdrawal(new Withdrawal());
        product.setInvestor(investor);
        return productRepository.save(product);
    }

    // TODO: Add security for the API

    // TODO: Add documentation for the API

    // TODO: Create a new Withdraw for a single product
//    @PostMapping("/investors/{investorId}/products/{productId}/withdraw")
//    public Withdrawal addWithdrawal(@PathVariable (value = "investorId") Long investorId, @PathVariable (value = "productId") Long productId, @RequestBody Withdrawal withdrawal) {
//        Product product = productRepository.findByIdAndInvestorId(productId, investorId)
//                .orElseThrow(() -> new ResourceNotFoundException("Product does not exist with id: " + productId + ", Investor does not exist with id: " + investorId));
//
//        withdrawal.setWithdrawalAmount(0.0);
//        withdrawal.setWithdrawalStatus(WithdrawalStatus.STARTED);
//        withdrawal.setProduct(product);
//
//        return withdrawalRepository.save(withdrawal);
//    }

    @PutMapping("/investors/{investorId}/products/{productId}/withdraw")
    public ResponseEntity<Product> withdrawProduct(@PathVariable (value = "investorId") Long investorId, @PathVariable (value = "productId") Long productId, @RequestBody Withdrawal withdrawal) {
        Investor investor = investorRepository.findById(investorId)
                .orElseThrow(() -> new ResourceNotFoundException("Investor does not exist with id: " + investorId));

        Product product = productRepository.findByIdAndInvestorId(productId, investorId)
                .orElseThrow(() -> new ResourceNotFoundException("Product does not exist with id: " + productId + ", Investor does not exist with id: " + investorId));

        // TODO: Add security with roles to access the endpoints and make changes
        // TODO: Validate the withdrawal amount, investor age and validation errors

        product.setProductName(product.getProductName());
        product.setProductType(product.getProductType());
        product.setInvestor(product.getInvestor());
        product.setProductCurrentBalance(validate(investor, product, withdrawal));
        product.setWithdrawal(new Withdrawal());

        Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    private static Double validate(Investor investor, Product product, Withdrawal withdrawal) {
        // Check Age
        LocalDate currentDate = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(investor.getInvestorDateOfBirth());

        if (product.getProductType() == ProductType.RETIREMENT && Period.between(birthDate, currentDate).getYears() < 65) {
            throw new IllegalStateException("The Investor is too young to withdraw from this product");
        }

        // Check Withdrawal Amount
        double ninetyPercent = product.getProductCurrentBalance() * 0.9;
        if (withdrawal.getWithdrawalAmount() > ninetyPercent) {
            throw new IllegalArgumentException("Withdrawal amount should not be more than ninety percent of the current balance");
        }

        double result = product.getProductCurrentBalance() - withdrawal.getWithdrawalAmount();
        return result;
    }

    @DeleteMapping("/investors/{investorId}/products/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable (value = "investorId") Long investorId, @PathVariable (value = "productId") Long productId) {
        Product product = productRepository.findByIdAndInvestorId(productId, investorId)
                .orElseThrow(() -> new ResourceNotFoundException("Product does not exist with id: " + productId + ", Investor does not exist with id: " + investorId));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
