package com.esad.services;

import com.esad.dto.PaymentDTO;

public class ReservationServiceImpl implements IReservationService {
    public void addReservation(PaymentDTO paymentDTO) {
        System.out.print(" " + paymentDTO.getLastEntryDescription() + "=" + paymentDTO
                .getLastEntryAmount());
    }
}
