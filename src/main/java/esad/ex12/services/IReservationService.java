package com.esad.services;

import com.esad.dto.PaymentDTO;

public interface IReservationService extends IService {

    void addReservation(PaymentDTO paymentDTO);
}
