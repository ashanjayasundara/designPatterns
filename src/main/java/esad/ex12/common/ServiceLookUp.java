package com.esad.common;

import com.esad.services.IService;
import com.esad.services.PaymentServiceImpl;
import com.esad.services.ReservationServiceImpl;

public class ServiceLookUp {

    public static IService lookUpService(String serviceName)
    {
        if (serviceName.equalsIgnoreCase("payment")) {
            System.out.print("Insert for payment ");
            return new PaymentServiceImpl();
        }
        else
            return new ReservationServiceImpl();
    }
}
