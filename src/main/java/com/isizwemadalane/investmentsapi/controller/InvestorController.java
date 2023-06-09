package com.isizwemadalane.investmentsapi.controller;

import com.isizwemadalane.investmentsapi.exception.ResourceNotFoundException;
import com.isizwemadalane.investmentsapi.model.Investor;
import com.isizwemadalane.investmentsapi.repository.InvestorRepository;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class InvestorController {

    @Autowired
    private InvestorRepository investorRepository;


    @Operation(summary = "Gets the list of all investors in the DB")
    @GetMapping("/investors")
    public List<Investor> getAllInvestors() {
        return investorRepository.findAll();
    }

    @Operation(summary = "Adds an investor to the DB")
    @PostMapping("/investors")
    public Investor createInvestor(@RequestBody Investor investor) {
        return investorRepository.save(investor);
    }

    @Operation(summary = "Gets a single investor by the id")
    @GetMapping("/investors/{id}")
    public ResponseEntity<Investor> getInvestorById(@PathVariable Long id) {
        Investor investor = investorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investor does not exist with id: " + id));
        return ResponseEntity.ok(investor);
    }

    @Operation(summary = "Updates the details of an investor")
    @PutMapping("/investors/{id}")
    public ResponseEntity<Investor> updateInvestor(@PathVariable Long id, @RequestBody Investor investorDetails) {
        Investor investor = investorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investor does not exist with id: " + id));

        investor.setInvestorName(investorDetails.getInvestorName());
        investor.setInvestorSurname(investorDetails.getInvestorSurname());
        investor.setInvestorDateOfBirth(investorDetails.getInvestorDateOfBirth());
        investor.setInvestorAddress(investorDetails.getInvestorAddress());
        investor.setInvestorMobileNumber(investorDetails.getInvestorMobileNumber());
        investor.setInvestorEmailAddress(investorDetails.getInvestorEmailAddress());
        investor.setProducts(investorDetails.getProducts());

        Investor updatedInvestor = investorRepository.save(investor);
        return ResponseEntity.ok(updatedInvestor);
    }

    @Operation(summary = "Deletes an investor using the id")
    @DeleteMapping("/investors/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteInvestor(@PathVariable Long id) {
        Investor investor = investorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investor does not exist with id: " + id));

        investorRepository.delete(investor);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
