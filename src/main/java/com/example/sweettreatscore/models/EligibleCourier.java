package com.example.sweettreatscore.models;

import java.math.BigDecimal;
import java.util.Objects;

public class EligibleCourier implements Comparable<EligibleCourier> {
    private Courier courier;
    private BigDecimal quote;

    public EligibleCourier(Courier courier, BigDecimal quote) {
        this.courier = courier;
        this.quote = quote;
    }

    public Courier getCourier() {
        return courier;
    }


    public BigDecimal getQuote() {
        return quote;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EligibleCourier that = (EligibleCourier) o;
        return Objects.equals(courier, that.courier) &&
                Objects.equals(quote, that.quote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courier, quote);
    }

    @Override
    public int compareTo(EligibleCourier o) {
        return this.getQuote().compareTo(o.getQuote());
    }

    @Override
    public String toString() {
        return "EligibleCourier{" +
                "courier=" + courier +
                ", quote=" + quote +
                '}';
    }
}
