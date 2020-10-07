package com.esad.controller;

import com.esad.common.ServiceDelegate;
import com.esad.dto.PaymentDTO;
import com.esad.services.*;

public class PaymentController {

	public static void main(String[] args)
	{
		PaymentDTO paymentDTO = new PaymentDTO();
		paymentDTO.setAmount(12000.00);
		paymentDTO.setDescription("Electricity Bill");
		(
			(IPaymentService)(ServiceDelegate.getService("payment") )
		)
				.addPayment(paymentDTO);
	}
}
