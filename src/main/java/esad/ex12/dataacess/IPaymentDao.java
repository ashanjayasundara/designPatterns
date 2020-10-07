package com.esad.dataacess;

public interface IPaymentDao {

	public void setAmount(double amount);
	
	public void setDescription(String string);
	
	public double getAmount();
	
	public String getDescription();
	
}
