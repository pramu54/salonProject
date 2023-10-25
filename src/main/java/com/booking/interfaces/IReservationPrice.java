package com.booking.interfaces;

import java.util.HashMap;

public interface IReservationPrice {
	HashMap<String, Double> discount = new HashMap<String, Double>(){/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	{
		put("none", 0.0);
		put("Silver", 0.05);
		put("Gold", 0.10);
	}};
	
	double calculateReservationPrice();
}
