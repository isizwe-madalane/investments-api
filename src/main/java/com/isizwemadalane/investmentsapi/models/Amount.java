package com.isizwemadalane.investmentsapi.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Embeddable
public class Amount implements Serializable {

    private BigDecimal value;

    @JsonCreator
    public Amount(BigDecimal value) {
        initValue(value);
    }

    public Amount(double value) {
        initValue(BigDecimal.valueOf(value));
    }

    private void initValue(BigDecimal value) {
        this.value = value.setScale(2, RoundingMode.HALF_EVEN);
    }

    public static Amount zero() {
        return new Amount(0);
    }

    public Amount withdraw(Amount amount) {
        return new Amount(value.subtract(amount.value));
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Amount)) {
            return false;
        }
        return value.equals(((Amount) obj).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "R" + value.toString();
    }
}
