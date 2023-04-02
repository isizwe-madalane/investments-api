package com.isizwemadalane.investmentsapi.service;

import com.isizwemadalane.investmentsapi.models.Investor;

public interface InvestorService {
    void createInvestor(Investor investor);
    void withdraw(int productId, double amount);
}
