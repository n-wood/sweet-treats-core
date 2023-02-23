package com.example.sweettreatscore.models;

import java.time.LocalTime;

public class OrderDetails {

    private LocalTime deliveryTime;
    private boolean fridgeRequired;
    private double distance;

    public OrderDetails(LocalTime deliveryTime, boolean fridgeRequired, double distance) {
        this.deliveryTime = deliveryTime;
        this.fridgeRequired = fridgeRequired;
        this.distance = distance;
    }

    public LocalTime getDeliveryTime() {
        return deliveryTime;
    }

    public boolean isFridgeRequired() {
        return fridgeRequired;
    }

    public double getDistance() {
        return distance;
    }
}
