package com.example.sweettreatscore.models;

import java.math.BigDecimal;
import java.time.LocalTime;

public class Courier {

    private String name;

    private boolean fridgeAvailable;

    private LocalTime shiftStartTime;

    private LocalTime shiftEndTime;

    private BigDecimal mileRate;

    private int maxDeliveryDistance;



    public Courier(String name, boolean fridgeAvailable, LocalTime shiftStartTime, LocalTime shiftEndTime, BigDecimal mileRate, int maxDeliveryDistance) {
        this.name = name;
        this.fridgeAvailable = fridgeAvailable;
        this.shiftStartTime = shiftStartTime;
        this.shiftEndTime = shiftEndTime;
        this.mileRate = mileRate;
        this.maxDeliveryDistance = maxDeliveryDistance;
    }

    public String getName() {
        return name;
    }

    public boolean isFridgeAvailable() {
        return fridgeAvailable;
    }

    public LocalTime getShiftStartTime() {
        return shiftStartTime;
    }

    public LocalTime getShiftEndTime() {
        return shiftEndTime;
    }

    public BigDecimal getMileRate() {
        return mileRate;
    }

    public int getMaxDeliveryDistance() {
        return maxDeliveryDistance;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "name='" + name + '\'' +
                '}';
    }
}
