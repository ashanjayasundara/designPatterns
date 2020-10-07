package com.esad.dto;

import java.util.ArrayList;
import java.util.List;
import  com.esad.dataacess.*;

public class PaymentDTO {
	List<IPaymentDao> paymentDaoList;
	public PaymentDTO()
	{
		paymentDaoList = new ArrayList<IPaymentDao>();
		this.createPaymentDAO();
	}
	public void createPaymentDAO() 
	{
		IPaymentDao paymentDAOtemp = new PaymentDAOImpl();
		paymentDaoList.add(paymentDAOtemp);
	}
	public void setAmount(double amount)
	{
		this.paymentDaoList.get(paymentDaoList.size()-1)
				.setAmount(amount);
	}
	public void setDescription(String description) 
	{
		this.paymentDaoList.get(paymentDaoList.size()-1)
				.setDescription(description);
	}
	public String getLastEntryDescription() 
	{
		return this.paymentDaoList.get(paymentDaoList.size()-1).getDescription();
	}
	public double getLastEntryAmount()
	{
		return this.paymentDaoList.get(paymentDaoList.size()-1).getAmount();
	}
}
