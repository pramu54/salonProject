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
        	final String customerTemp = customerId;
        	
        	isCustomerValid = customerList.stream()
        						.filter(person -> person instanceof Customer)
        						.map(customer -> (Customer) customer)
        						.anyMatch(customer -> customer.getId().equalsIgnoreCase(customerTemp));
        	
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
        	final String employeeTemp = employeeId;
        	
        	/*
	    	for (Person employee : employeeList) {
				if(employee instanceof Employee) {
					if(employeeId.equalsIgnoreCase(employee.getId())) {
						isEmployeeValid = true;
						break;
					} 
				}
			}
			*/
        	isEmployeeValid = employeeList.stream()
								.filter(person -> person instanceof Employee)
								.map(employee -> (Employee) employee)
								.anyMatch(employee -> employee.getId().equalsIgnoreCase(employeeTemp));
	    	
	    	if(!isEmployeeValid) {
	    		System.out.println("Employee yang dicari tidak ditemukan.");
	    	}
        }while(!isEmployeeValid);
    	
    	return employeeId;
    }
    
    public static boolean validateServiceId(List<Service> serviceList, String serviceId){
        boolean isServiceValid = false;
    	
        /*
    	for (Service service : serviceList) {
			if(serviceId.equalsIgnoreCase(service.getServiceId())) {
				isServiceValid = true;
				break;
			} 
		}
		*/
        
        isServiceValid = serviceList.stream()
							.anyMatch(service -> service.getServiceId().equalsIgnoreCase(serviceId));
    	
    	if(!isServiceValid) {
    		System.out.println("Service yang dicari tidak ditemukan.");
    	}
    	
    	return isServiceValid;
    }
    
    public static boolean validateUniqueServiceId(List<String> servicesId, String serviceId) {
    	boolean isNotUnique = false;
    	
    	/*
    	for(String id : servicesId) {
    		if(serviceId.equalsIgnoreCase(id)) {
    			isUnique = false;
    		}
    	}
    	*/
    	
    	isNotUnique = servicesId.stream()
				.anyMatch(service -> service.equalsIgnoreCase(serviceId));
    	
    	if(isNotUnique) {
    		System.out.println("Service sudah dipilih");
    	} 
    	
    	
    	return isNotUnique;
    }
}
