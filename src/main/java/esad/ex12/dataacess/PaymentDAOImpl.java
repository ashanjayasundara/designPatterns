package com.esad.dataacess;

public class PaymentDAOImpl implements IPaymentDao {
	double amount;
	String description;

	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	public void setDescription(String string) 
	{
		this.description = string;
	}
	
	public double getAmount()
	{
		return this.amount;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
}
