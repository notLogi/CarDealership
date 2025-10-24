package com.pluralsight;

import java.util.Scanner;
import java.util.List;

public class UserInterface {
    private Dealership dealership;

    Scanner scanner = new Scanner(System.in);

    UserInterface(){}

    public void display(){
        dealership = init();
        boolean didExit = false;
        while(!didExit){
            System.out.println("What do you want to do? Here's a list: ");//Concatenation can be replaced with text block(Intellij suggested)
            System.out.println("""
                    1 - Find vehicles within a price range
                    2 - Find vehicles by make / model
                    3 - Find vehicles by year range
                    4 - Find vehicles by color
                    5 - Find vehicles by mileage range
                    6 - Find vehicles by type (car, truck, SUV, van)
                    7 - List ALL vehicles
                    8 - Add a vehicle
                    9 - Remove a vehicle
                    99 - Quit""");
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
        System.out.println("What is the minimum price: ");
        double minPrice = scanner.nextDouble();
        System.out.println("What is the maximum price: ");
        double maxPrice = scanner.nextDouble();
        displayVehicles(dealership.getVehiclesByPrice(minPrice, maxPrice));
    }
    public void processGetByMakeModelRequest(){
        System.out.println("Enter maker: ");
        String make = scanner.nextLine();
        System.out.println("Enter model: ");
        String model = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }
    public void processGetByYearRequest(){
        System.out.println("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.println("Enter maximum year: ");
        int maxYear = scanner.nextInt();
        displayVehicles(dealership.getVehiclesByYear(minYear, maxYear));
    }
    public void processGetByColorRequest(){
        System.out.println("Enter color you want to filter: ");
        String color = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByColor(color));
    }
    public void processGetByMileageRequest(){
        System.out.println("Enter your minimal mileage: ");
        int minMileage = scanner.nextInt();
        System.out.println("Enter your maximum mileage: ");
        int maxMileage = scanner.nextInt();
        displayVehicles(dealership.getVehiclesByMileage(minMileage, maxMileage));
    }

    public void processGetByVehicleTypeRequest(){
        System.out.println("Enter your preferred vehicle type: ");
        String vehicleType = scanner.nextLine();
        displayVehicles((dealership.getVehiclesByType(vehicleType)));
    }
    public void processGetAllVehiclesRequest(){
        displayVehicles(dealership.getAllVehicles());
    }
    public void processAddVehicleRequest(){
        try{
            System.out.println("Do you want to add a vehicle? Start off with the vin first if so. If not, type 0 to exit.");
            int vin = scanner.nextInt();
            if(vin == 0) return;
            System.out.println("Input year of vehicle: ");
            int year = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Input maker of vehicle: ");
            String make = scanner.nextLine();
            System.out.println("Input model of vehicle: ");
            String model = scanner.nextLine();
            System.out.println("Input vehicle type of vehicle: ");
            String vehicleType = scanner.nextLine();
            System.out.println("Input color of vehicle: ");
            String color = scanner.nextLine();
            System.out.println("Input the mileage of vehicle: ");
            int odometer = scanner.nextInt();
            System.out.println("Input the price of vehicle: ");
            double price = scanner.nextDouble();
            dealership.addVehicle(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
            System.out.println("Vehicle added successfully");
        }
        catch(Exception ex){
            System.err.println("You have entered a string instead of an integer for vin, year, mileage, or price");
        }
    }
    public void processRemoveVehicleRequest(){
        System.out.println("Select a vehicle to remove from your inventory. Enter 0 if you want to exit.");
        int vin = scanner.nextInt();
        if(vin == 0) return;
        for(Vehicle vehicle : dealership.getAllVehicles()){
            if(vin == vehicle.getVin()){
                dealership.removeVehicle(vehicle);
                return;
            }
        }
        System.out.println("No vehicle exist in database.");
    }

    private void displayVehicles(List<Vehicle> vehicleList){
        for(Vehicle vehicle : vehicleList){
            System.out.println(vehicle.toString());
        }
    }
}
