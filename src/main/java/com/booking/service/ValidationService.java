package com.booking.service;

import java.util.List;
import java.util.Scanner;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Service;

public class ValidationService {
    // Buatlah function sesuai dengan kebutuhan
	private static Scanner input = new Scanner(System.in);
	
    public static void validateInput(String input, String regex){
    	
    }

    public static String validateCustomerId(List<Person> customerList){
        boolean isCustomerValid = false;
        String customerId;
    	
        do {
        	System.out.println("Silahkan masukkan Customer ID : ");
        	System.out.print("Cust-");
        	customerId = "Cust-" + input.nextLine();
        	
        	for (Person customer : customerList) {
    			if(customer instanceof Customer) {
    				if(customerId.equalsIgnoreCase(customer.getId())) {
    					isCustomerValid = true;
    					break;
    				} 
    			}
    		}
        	
        	if(!isCustomerValid) {
        		System.out.println("Customer yang dicari tidak ditemukan.");
        	}
        }while(!isCustomerValid);
    	
    	return customerId;
    }
    
    public static String validateEmployeeId(List<Person> employeeList){
        boolean isEmployeeValid = false;
        String employeeId;
    	
        do {
    		System.out.println("Silahkan masukkan Employee ID : ");
    		System.out.print("Emp-");
        	employeeId = "Emp-" + input.nextLine();
        	
	    	for (Person employee : employeeList) {
				if(employee instanceof Employee) {
					if(employeeId.equalsIgnoreCase(employee.getId())) {
						isEmployeeValid = true;
						break;
					} 
				}
			}
	    	
	    	if(!isEmployeeValid) {
	    		System.out.println("Employee yang dicari tidak ditemukan.");
	    	}
        }while(!isEmployeeValid);
    	
    	return employeeId;
    }
    
    public static boolean validateServiceId(List<Service> serviceList, String serviceId){
        boolean isServiceValid = false;
    	
    	for (Service service : serviceList) {
			if(serviceId.equalsIgnoreCase(service.getServiceId())) {
				isServiceValid = true;
				break;
			} 
		}
    	
    	if(!isServiceValid) {
    		System.out.println("Service yang dicari tidak ditemukan.");
    	}
    	
    	return isServiceValid;
    }
    
    public static boolean validateUniqueServiceId(List<String> servicesId, String serviceId) {
    	boolean isUnique = true;
    	
    	for(String id : servicesId) {
    		if(serviceId.equalsIgnoreCase(id)) {
    			isUnique = false;
    		}
    	}
    	
    	if(!isUnique) {
    		System.out.println("Service sudah dipilih");
    	}
    	
    	return isUnique;
    }
}
