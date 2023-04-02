package com.isizwemadalane.investmentsapi.service;

import com.isizwemadalane.investmentsapi.models.Investor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class InvestorServiceImpl implements InvestorService {

    private JdbcTemplate jdbcTemplate;

    public InvestorServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createInvestor(Investor investor) {

    }

    @Override
    public void withdraw(int productId, double amount) {

    }
}
