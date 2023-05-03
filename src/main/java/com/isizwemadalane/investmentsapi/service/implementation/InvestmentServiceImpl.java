package com.isizwemadalane.investmentsapi.service.implementation;

import com.isizwemadalane.investmentsapi.model.Investor;
import com.isizwemadalane.investmentsapi.model.Product;
import com.isizwemadalane.investmentsapi.model.ProductType;
import com.isizwemadalane.investmentsapi.service.InvestmentService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class InvestmentServiceImpl implements InvestmentService {

    public Double validate(Investor investor, Product product) {
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
