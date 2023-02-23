package com.example.sweettreatscore.services;


import com.example.sweettreatscore.models.OrderDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.sweettreatscore.models.Courier;
import com.example.sweettreatscore.models.EligibleCourier;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class SelectCourierTest {


    CourierService serviceUnderTest;

    @BeforeEach
    void setUp() {
        Courier bobby = new Courier("Bobby",
                true,
                LocalTime.of(9,0),
                LocalTime.of(13,0),
                new BigDecimal("1.75"),
                5);
        Courier martin = new Courier("Martin",
                false,
                LocalTime.of(9,0),
                LocalTime.of(17,0),
                new BigDecimal("1.5"),
                3);
        Courier geoff = new Courier("Geoff",
                true,
                LocalTime.of(10,0),
                LocalTime.of(16,0),
                new BigDecimal("2"),
                4);

        List<Courier> couriers = new ArrayList<Courier>();
        couriers.add(bobby);
        couriers.add(martin);
        couriers.add(geoff);

        serviceUnderTest = new CourierService(couriers);
    }

    @Test
    public void getBestCourier() {
        Optional<EligibleCourier> expectingBobby = serviceUnderTest.getBestCourier(new OrderDetails(LocalTime.of(9, 0), true, 5));
        Optional<EligibleCourier> expectingMartin = serviceUnderTest.getBestCourier(new OrderDetails(LocalTime.of(17, 0), false, 1));
        Optional<EligibleCourier> expectingGeoff = serviceUnderTest.getBestCourier(new OrderDetails(LocalTime.of(16, 0), true, 4));
        assertAll(
                () -> assertEquals("Bobby",expectingBobby.get().getCourier().getName()),
                () -> assertEquals("Martin",expectingMartin.get().getCourier().getName() ),
                () -> assertEquals("Geoff",expectingGeoff.get().getCourier().getName() )
        );
    }
    @Test
    public void noCourierAvailable() {
        Optional<EligibleCourier> nobody = serviceUnderTest.getBestCourier(new OrderDetails(LocalTime.of(8, 59), false, 1));
        assertTrue(nobody.isEmpty());

    }

    @Test
    public void testListCouriers() {
        List<EligibleCourier> expectingMartinBobbyAndGeoff = serviceUnderTest.getEligibleCouriers(
                new OrderDetails(LocalTime.of(10, 0), false, 3));

        assertAll(
                () -> assertEquals( 3, expectingMartinBobbyAndGeoff.size()),
                () -> assertEquals("Martin", expectingMartinBobbyAndGeoff.get(0).getCourier().getName(), "First choice"),
                () -> assertEquals("Bobby", expectingMartinBobbyAndGeoff.get(1).getCourier().getName(), "Second choice"),
                () -> assertEquals("Geoff", expectingMartinBobbyAndGeoff.get(2).getCourier().getName(), "Third choice")
        );
    }

    @Test
    public void testListOfOneCouriers() {
        List<EligibleCourier> expectingMartin = serviceUnderTest.getEligibleCouriers(
                new OrderDetails(LocalTime.of(17, 0), false, 3));

        assertAll(
                () -> assertEquals( 1, expectingMartin.size()),
                () -> assertEquals("Martin", expectingMartin.get(0).getCourier().getName(), "First choice")
        );
    }

    @Test
    public void testListOfZeroCouriers() {
        List<EligibleCourier> expectingNone = serviceUnderTest.getEligibleCouriers(
                new OrderDetails(LocalTime.of(18, 0), false, 3));
        assertEquals( 0, expectingNone.size());
    }




}
