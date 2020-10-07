package com.esad.services;

import com.esad.dto.PaymentDTO;

public class PaymentServiceImpl implements IPaymentService  {


	public void addPayment(PaymentDTO paymentDTO) {
		System.out.print(" " + paymentDTO.getLastEntryDescription() + "=" + paymentDTO
				.getLastEntryAmount());
	}


}
