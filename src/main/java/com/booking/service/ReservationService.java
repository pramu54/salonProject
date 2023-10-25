package com.booking.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class ReservationService {
	private static Scanner input = new Scanner(System.in);
	private static DecimalFormat rupiah = new DecimalFormat("#,###");
	
    public static Reservation createReservation(List<Person> personList, List<Service> serviceList, List<Reservation> reservationList){
    	String serviceIdTemp;
        String addService;
        
    	boolean isServiceValid = false;
    	boolean isUnique = false;
    	boolean isServiceIdDone = false;

    	String reservationId = "Rsv-" + String.format("%02d", reservationList.size() + 1);
    	
        List<String> serviceId = new ArrayList<String>();
    	
    	PrintService.showAllCustomer(personList);
    	
    	Customer customerTemp = ReservationService.getCustomerByCustomerId(personList, ValidationService.validateCustomerId(personList));
    
    	PrintService.showAllEmployee(personList);
    	
    	Employee employeeTemp = ReservationService.getEmployeeByEmployeeId(personList, ValidationService.validateEmployeeId(personList));
    	
    	PrintService.showServices(serviceList);
    	
    	do {
    		do {
    			System.out.println("Silahkan masukkan Service ID : ");
    			System.out.print("Serv-");
            	serviceIdTemp = "Serv-" + input.nextLine();
            	
            	isServiceValid = ValidationService.validateServiceId(serviceList, serviceIdTemp);
            	if(serviceId.isEmpty()) {
            		isUnique = true;
            	} else {
            		isUnique = ValidationService.validateUniqueServiceId(serviceId, serviceIdTemp);
            	}
    		}while(!isServiceValid || !isUnique);
    		                    	
    		serviceId.add(serviceIdTemp);
    		System.out.println("Tambahkan service lain? (ya/tidak)");
    		addService = input.nextLine();
    		if(addService.equalsIgnoreCase("tidak") || serviceId.size() == serviceList.size()) {
    			isServiceIdDone = true;
    		}
    	}while(!isServiceIdDone);
    	
    	List<Service> servicesTemp = new ArrayList<Service>();
    	for(String service : serviceId) {
    		servicesTemp.add(ReservationService.getServiceById(serviceList, service));
    	}
    	
    	String workstage = "In Process";
    	
    	Reservation reservationTemp = new Reservation(reservationId, customerTemp, employeeTemp, servicesTemp, workstage);
    	
    	System.out.println("Booking Berhasil!");
    	System.out.println("Total Biaya Booking : Rp." + rupiah.format(reservationTemp.calculateReservationPrice()).replace(',', '.'));
    	
    	return reservationTemp;
    }

    public static Customer getCustomerByCustomerId(List<Person> personList, String customerId){
    	Customer customer = new Customer();
        
    	for (Person person : personList) {
			if(person instanceof Customer) {
				if(person.getId().equalsIgnoreCase(customerId)) {
					customer = (Customer) person;
				}
			}
		}
    	
        return customer;
    }
    
    public static Employee getEmployeeByEmployeeId(List<Person> personList, String employeeId){
    	Employee customer = new Employee();
        
    	for (Person person : personList) {
			if(person instanceof Employee) {
				if(person.getId().equalsIgnoreCase(employeeId)) {
					customer = (Employee) person;
				}
			}
		}
    	
        return customer;
    }
    
    public static Service getServiceById(List<Service> serviceList, String serviceId) {
    	Service service = new Service();
    	
    	for(Service s : serviceList) {
    		if(s.getServiceId().equalsIgnoreCase(serviceId)) {
    			service = s;
    		}
    	}
    	
		return service;
    }
    
    public static Reservation getReservationById(List<Reservation> reservationList, String reservationId) {
    	Reservation reservationTemp = new Reservation();
    	
    	for (Reservation reservation : reservationList) {
			if(reservation.getReservationId().equalsIgnoreCase(reservationId)) {
				reservationTemp = reservation;
				break;
			}
		}
    	
    	return reservationTemp;
    }
    
    public static int getReservationIndex(List<Reservation> reservationList, String reservationId) {
    	int index = 0;
    	
    	for(int i = 0; i < reservationList.size(); i++) {
    		if(reservationList.get(i).getReservationId().equalsIgnoreCase(reservationId)) {
    			index = i;
    			break;
    		}
    	}
    	
    	return index;
    }

    public static Reservation editReservationWorkstage(Reservation reservation, String workspace){
        Reservation result = new Reservation();
        
        result = reservation;
        result.setWorkstage(workspace);
        
        return result;
    }

    // Silahkan tambahkan function lain, dan ubah function diatas sesuai kebutuhan
}
