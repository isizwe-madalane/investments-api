package com.isizwemadalane.investmentsapi.controller;

import com.isizwemadalane.investmentsapi.exception.ResourceNotFoundException;
import com.isizwemadalane.investmentsapi.model.Investor;
import com.isizwemadalane.investmentsapi.model.Product;
import com.isizwemadalane.investmentsapi.repository.InvestorRepository;
import com.isizwemadalane.investmentsapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/investors/{investorId}/products")
    public Page<Product> getAllProductsByInvestorId(@PathVariable (value = "investorId") Long investorId, Pageable pageable) {
        return productRepository.findByInvestorId(investorId, pageable);
    }

    @PostMapping("/investors/{investorId}/products")
    public Product addProduct(@PathVariable (value = "investorId") Long investorId, @RequestBody Product product) {
        Investor investor = investorRepository.findById(investorId)
                .orElseThrow(() -> new ResourceNotFoundException("Investor does not exist with id: " + investorId));

        product.setInvestor(investor);
        return productRepository.save(product);
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
