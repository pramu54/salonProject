package com.booking.service;

import java.text.DecimalFormat;
import java.util.List;

import com.booking.models.Customer;
import com.booking.models.Employee;
import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;

public class PrintService {
	private static DecimalFormat rupiah = new DecimalFormat("#,###");
	
    public static void printMenu(String title, String[] menuArr){
        int num = 1;
        System.out.println(title);
        for (int i = 0; i < menuArr.length; i++) {
            if (i == (menuArr.length - 1)) {   
                num = 0;
            }
            System.out.println(num + ". " + menuArr[i]);   
            num++;
        }
    }

    public static String printServices(List<Service> serviceList){
        String result = "";
        // Bisa disesuaikan kembali
        for (Service service : serviceList) {
            result += service.getServiceName() + ", ";
        }
        return result;
    }
    
    public static void showServices(List<Service> serviceList) {
    	int num = 1;
    	System.out.println("+=======================================================+");
        System.out.printf("| %-4s | %-10s | %-20s | %-10s |\n",
                "No.", "ID", "Nama Service", "Harga");
        System.out.println("+=======================================================+");
        for (Service service : serviceList) {
            System.out.printf("| %-4s | %-10s | %-20s | %-10s |\n",
            num, service.getServiceId(), service.getServiceName(), "Rp." + rupiah.format(service.getPrice()).replace(',', '.'));
            num++;
        }
        System.out.println("+=======================================================+");
    }

    // Function yang dibuat hanya sebgai contoh bisa disesuaikan kembali
    public static void showRecentReservation(List<Reservation> reservationList){
        int num = 1;
        System.out.printf("| %-4s | %-10s | %-15s | %-50s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+======================================================================================================================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Waiting") || reservation.getWorkstage().equalsIgnoreCase("In process")) {
                System.out.printf("| %-4s | %-10s | %-15s | %-50s | %-15s | %-15s | %-10s |\n",
                num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), "Rp." + rupiah.format(reservation.getReservationPrice()).replace(',', '.'), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
        }
        System.out.println("+======================================================================================================================================================================================+");
    }

    public static void showAllCustomer(List<Person> customerList){
    	int num = 1;
    	System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Alamat", "Membership", "Uang");
        System.out.println("+========================================================================================+");
        for (Person customer : customerList) {
        	if(customer instanceof Customer) {
        		System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-15s | %-10s |\n",
                num, customer.getId(), customer.getName(), customer.getAddress(), ((Customer) customer).getMember().getMembershipName(), "Rp." + rupiah.format(((Customer) customer).getWallet()).replace(',', '.'));
                num++;
        	}
        }
    }

    public static void showAllEmployee(List<Person> employeeList){
    	int num = 1;
    	System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Alamat", "Pengalaman");
        System.out.println("+========================================================================================+");
        for (Person employee : employeeList) {
        	if(employee instanceof Employee) {
        		System.out.printf("| %-4s | %-4s | %-11s | %-15s | %-10s |\n",
                num, employee.getId(), employee.getName(), employee.getAddress(), ((Employee) employee).getExperience());
                num++;
        	}
        }
    }

    public static void showHistoryReservation(List<Reservation> reservationList){
    	int num = 1;
    	double total = 0;
        System.out.printf("| %-4s | %-10s | %-15s | %-50s | %-15s | %-15s | %-10s |\n",
                "No.", "ID", "Nama Customer", "Service", "Biaya Service", "Pegawai", "Workstage");
        System.out.println("+======================================================================================================================================================================================+");
        for (Reservation reservation : reservationList) {
            if (reservation.getWorkstage().equalsIgnoreCase("Finish") || reservation.getWorkstage().equalsIgnoreCase("Cancel")) {
                System.out.printf("| %-4s | %-10s | %-15s | %-50s | %-15s | %-15s | %-10s |\n",
                num, reservation.getReservationId(), reservation.getCustomer().getName(), printServices(reservation.getServices()), "Rp." + rupiah.format(reservation.getReservationPrice()).replace(',', '.'), reservation.getEmployee().getName(), reservation.getWorkstage());
                num++;
            }
            if (reservation.getWorkstage().equalsIgnoreCase("Finish")) {
            	total += reservation.getReservationPrice();
            }
        }
        System.out.println("+======================================================================================================================================================================================+");
        System.out.println("Total Keuntungan : Rp." + rupiah.format(total).replace(',', '.'));
    }
}
