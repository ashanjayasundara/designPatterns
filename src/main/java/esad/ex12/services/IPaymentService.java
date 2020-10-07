package com.esad.services;

import com.esad.dto.PaymentDTO;

public interface IPaymentService extends IService{

	void addPayment(PaymentDTO paymentDTO);

}
