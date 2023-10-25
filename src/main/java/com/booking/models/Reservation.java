package com.booking.models;

import java.util.List;

import com.booking.interfaces.IReservationPrice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reservation implements IReservationPrice{
    private String reservationId;
    private Customer customer;
    private Employee employee;
    private List<Service> services;
    private double reservationPrice;
    private String workstage;
    //   workStage (In Process, Finish, Canceled)

    public Reservation(String reservationId, Customer customer, Employee employee, List<Service> services,
            String workstage) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.employee = employee;
        this.services = services;
        this.reservationPrice = calculateReservationPrice();
        this.workstage = workstage;
    }

	@Override
	public double calculateReservationPrice() {
		// TODO Auto-generated method stub
		double price = 0;
		
		for (Service service : services) {
			price += service.getPrice() * discount.get(customer.getMember().getMembershipName()); 
		}
		
		return price;
	};
}
