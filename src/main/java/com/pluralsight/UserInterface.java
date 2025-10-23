package com.pluralsight;

import java.util.Scanner;
import java.util.List;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    Dealership dealership;

    UserInterface(){}

    public void display(){
        dealership = init();
        boolean didExit = false;
        while(!didExit){
            System.out.println("What do you want to do? Here's a list: ");
            System.out.println("1 - Find vehicles within a price range\n" +
                    "2 - Find vehicles by make / model\n" +
                    "3 - Find vehicles by year range\n" +
                    "4 - Find vehicles by color\n" +
                    "5 - Find vehicles by mileage range\n" +
                    "6 - Find vehicles by type (car, truck, SUV, van)\n" +
                    "7 - List ALL vehicles\n" +
                    "8 - Add a vehicle\n" +
                    "9 - Remove a vehicle\n" +
                    "99 - Quit");
            int input = scanner.nextInt();
            scanner.nextLine();
            switch(input){
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 99 -> didExit = true;
                default -> System.out.println("Invalid input");
            }
        }
    }

    private Dealership init(){
        DealershipFileManager dealerManager = new DealershipFileManager();
        return dealerManager.getDealership();
    }

    public void processGetByPriceRequest(){

    }
    public void processGetByMakeModelRequest(){

    }
    public void processGetByYearRequest(){

    }
    public void processGetByColorRequest(){

    }
    public void processGetByMileageRequest(){

    }

    public void processGetByVehicleTypeRequest(){

    }
    public void processGetAllVehiclesRequest(){
        displayVehicles(dealership.getAllVehicles());
    }
    public void processAddVehicleRequest(){

    }
    public void processRemoveVehicleRequest(){

    }

    private void displayVehicles(List<Vehicle> vehicleList){
        for(Vehicle vehicle : vehicleList){
            System.out.println(vehicle.toString());
        }
    }


}
