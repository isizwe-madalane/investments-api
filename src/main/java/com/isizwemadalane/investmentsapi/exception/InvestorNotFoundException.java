package com.isizwemadalane.investmentsapi.exception;

public class InvestorNotFoundException extends RuntimeException {

    public InvestorNotFoundException(Long id) {
        super("Could not find investor id: " + id);
    }
}
