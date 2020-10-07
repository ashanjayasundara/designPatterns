package com.esad.common;

import com.esad.services.*;

public class ServiceDelegate {

	public static IPaymentService getService(String serviceName)
	{
		IPaymentService paymentService
				= (IPaymentService) ServiceLookUp.lookUpService("payment");
		return paymentService;
	}
	
}
