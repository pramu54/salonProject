package com.booking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.booking.models.Person;
import com.booking.models.Reservation;
import com.booking.models.Service;
import com.booking.repositories.PersonRepository;
import com.booking.repositories.ServiceRepository;

public class MenuService {
    private static List<Person> personList = PersonRepository.getAllPerson();
    private static List<Service> serviceList = ServiceRepository.getAllService();
    private static List<Reservation> reservationList = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void mainMenu() {
        String[] mainMenuArr = {"Show Data", "Create Reservation", "Complete/cancel reservation", "Exit"};
        String[] subMenuArr = {"Recent Reservation", "Show Customer", "Show Available Employee", "Show Reservation History", "Back to main menu"};
    
        int optionMainMenu;
        int optionSubMenu;

		boolean backToMainMenu = false;
        boolean backToSubMenu = false;
        
        do {
            PrintService.printMenu("Main Menu", mainMenuArr);
            optionMainMenu = Integer.valueOf(input.nextLine());
            switch (optionMainMenu) {
                case 1:
                    do {
                        PrintService.printMenu("Show Data", subMenuArr);
                        optionSubMenu = Integer.valueOf(input.nextLine());
                        // Sub menu - menu 1
                        switch (optionSubMenu) {
                            case 1:
                            	PrintService.showRecentReservation(reservationList);
                                break;
                            case 2:
                                PrintService.showAllCustomer(personList);
                                break;
                            case 3:
                                PrintService.showAllEmployee(personList);
                                break;
                            case 4:
                                PrintService.showHistoryReservation(reservationList);
                                break;
                            case 0:
                                backToSubMenu = true;
                                break;
                            default:
                            	System.out.println("Menu tidak ditemukan. Masukkan lagi!");
                        }
                    } while (!backToSubMenu);
                    break;
                case 2:
                	reservationList.add(ReservationService.createReservation(personList, serviceList, reservationList));
                	System.out.println();
                    break;
                case 3:
                	String workStage;
                	boolean isWorkStageValid = false;
                	boolean isValid = false;
                	String reservationId;
                	int reservationIndex;
                	
                	Reservation reservationTemp = new Reservation();
                	Reservation reservationNew = new Reservation();
                	
                	PrintService.showRecentReservation(reservationList);
                	
                	do {
                		System.out.println("Silahkan Masukkan Reservation Id");
            	    	System.out.print("Rsv-");
            	    	reservationId = "Rsv-" + input.nextLine();
            	    	
            	    	for (Reservation reservation : reservationList) {
            				if(reservation.getReservationId().equalsIgnoreCase(reservationId)) {
            					isValid = true;
            					break;
            				}
            			}
            	    	
            	    	if(!isValid) {
            	    		System.out.println("Reservasi tidak ditemukan!");
            	    	}
                	}while(!isValid);
                	
                	do {
                		System.out.println("Selesaikan Reservasi (Finish/Cancel)");
                		workStage = input.nextLine();
            	    	
        				if(workStage.equalsIgnoreCase("Finish") || workStage.equalsIgnoreCase("Cancel")) {
        					isWorkStageValid = true;
        					break;
        				}
            	    	
            	    	if(!isWorkStageValid) {
            	    		System.out.println("Workstage tidak valid!");
            	    	}
                	}while(!isWorkStageValid);
                	
                	reservationTemp = ReservationService.getReservationById(reservationList, reservationId);
                	reservationIndex = ReservationService.getReservationIndex(reservationList, reservationId);
                	
                	reservationNew = reservationTemp;
                	reservationNew.setWorkstage(workStage);
                	
                	reservationList.set(reservationIndex, reservationNew);
                	
                	System.out.println("Reservasi selesai dilakukan");
                	
                    break;
                case 0:
                	System.out.println("Terima Kasih Telah Menggunakan Aplikasi Ini!");
                    backToMainMenu = true;
                    break;
                default:
                	System.out.println("Menu tidak ditemukan! Masukkan lagi");
            }
        } while (!backToMainMenu);
		
	}
}
