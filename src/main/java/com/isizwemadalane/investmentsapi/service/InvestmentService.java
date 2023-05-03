package com.isizwemadalane.investmentsapi.service;


import com.isizwemadalane.investmentsapi.model.Investor;
import com.isizwemadalane.investmentsapi.model.Product;

public interface InvestmentService {

    public Double validate(Investor investor, Product product);
}
