package com.isizwemadalane.investmentsapi.controller;

import com.isizwemadalane.investmentsapi.models.Investor;
import com.isizwemadalane.investmentsapi.models.Product;
import com.isizwemadalane.investmentsapi.models.Withdrawal;
import com.isizwemadalane.investmentsapi.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvestorController {

    @Autowired
    private InvestorRepository repository;

    @PostMapping("/{id}/withdrawals")
    public Investor createWithdrawal(@PathVariable Long id, @RequestBody Withdrawal withdrawal) {
        Investor investor = repository.findById(id)
                .orElseThrow(); // TODO: Add Exceptions for no Investor matching the id
//        Product product = investor.getProductById(withdrawal.getProductId);
//        product.addWithdrawal(new Withdrawal(withdrawal.getAmount()));

        Investor savedInvestor = repository.save(investor);
        return savedInvestor;
    }


}
