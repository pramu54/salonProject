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
    	boolean isNotUnique = true;
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
            		isNotUnique = false;
            	} else {
            		isNotUnique = ValidationService.validateUniqueServiceId(serviceId, serviceIdTemp);
            	}
    		}while(!isServiceValid || isNotUnique);
    		                    	
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
    	
    	return personList.stream()
    			.filter(person -> person instanceof Customer)
    			.map(customer -> (Customer) customer)
    			.filter(customer -> customer.getId().equalsIgnoreCase(customerId))
    			.findFirst()
    			.orElse(null);
    }
    
    /* Contoh stream()
     * 
     * public static List<CommercialBook> getBookByCountryAuthorV3(List<CommercialBook> allBook, String country){
		List<CommercialBook> bookByCountry = new ArrayList<CommercialBook>();
		
		allBook.stream()
		.filter(data -> data.getAuthor().getCountry().equalsIgnoreCase(country))
		.forEach((data) -> {
			bookByCountry.add(data);
		});
		
		return bookByCountry;
	}
	
	public static List<CommercialBook> getComicByRatingMangakaV2(List<CommercialBook> listAllBook, RatingAuthorEnum rating, List<CommercialBook> listComic) {

		listAllBook
	        .stream()
	        .filter(item -> item instanceof Comic)
	        .map(commercialBook -> (Comic) commercialBook)
	        .filter(bookComic -> bookComic.getAuthor().getRating() == rating)
	        .forEach(data -> {// Data Object Comic berdasarkan Rating
	        	listComic.add(data);
	        });
		
        return list
     */
    
    public static Employee getEmployeeByEmployeeId(List<Person> personList, String employeeId){
    	
    	return personList.stream()
    			.filter(person -> person instanceof Employee)
    			.map(employee -> (Employee) employee)
    			.filter(employee -> employee.getId().equalsIgnoreCase(employeeId))
    			.findFirst()
    			.orElse(null);
    }
    
    public static Service getServiceById(List<Service> serviceList, String serviceId) {
    	
    	return serviceList.stream()
    			.filter(service -> service.getServiceId().equalsIgnoreCase(serviceId))
    			.findFirst()
    			.orElse(null);
    }
    
    public static Reservation getReservationById(List<Reservation> reservationList, String reservationId) {
    	
    	return reservationList.stream()
    			.filter(service -> service.getReservationId().equalsIgnoreCase(reservationId))
    			.findFirst()
    			.orElse(null);
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
