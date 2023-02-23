package com.example.sweettreatscore.services;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.sweettreatscore.models.Courier;
import com.example.sweettreatscore.models.EligibleCourier;
import com.example.sweettreatscore.models.OrderDetails;

public class CourierService {

    private List<Courier> couriers;

    public CourierService(List<Courier> couriers) {
        this.couriers = couriers;
    }


    public Optional <EligibleCourier> getBestCourier(OrderDetails order) {

        List<EligibleCourier> eligible = getEligibleCouriers(order);
        return eligible.stream().findFirst();


    }

    public List<EligibleCourier> getEligibleCouriers(OrderDetails order) {

        List<EligibleCourier> eligible = new ArrayList<>();

        for (Courier courier : couriers) {
            if (isCourierEligible(order, courier)) {
                eligible.add(
                        new EligibleCourier(courier,
                                getCostEstimate(order, courier)));
            }
        }
        Collections.sort(eligible);
        return eligible;

    }


    private BigDecimal getCostEstimate(OrderDetails order, Courier courier) {
        BigDecimal calc = courier.getMileRate().multiply(new BigDecimal(order.getDistance()));
        calc.setScale(2);
        return calc;
    }

    private boolean isCourierEligible(OrderDetails order, Courier courier){
        return ( ((!order.isFridgeRequired()) || (order.isFridgeRequired() && courier.isFridgeAvailable()))  &&
                isWithinWorkingHours(order.getDeliveryTime(), courier) &&
                isInDeliveryRange(order.getDistance(), courier));
    }

    private boolean isWithinWorkingHours(LocalTime deliveryDate, Courier courier) {

        return (deliveryDate.equals(courier.getShiftStartTime()) ||
                    deliveryDate.isAfter(courier.getShiftStartTime()))
                &&
               (deliveryDate.equals(courier.getShiftEndTime()) ||
                       deliveryDate.isBefore(courier.getShiftEndTime()));

    }

    private boolean isInDeliveryRange(double deliveryDistance, Courier courier)
    {
        return  deliveryDistance <= courier.getMaxDeliveryDistance();
    }



}
