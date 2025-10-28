package com.pluralsight;

import java.util.Scanner;
import java.util.List;

public class UserInterface {
    Dealership dealership;

    Scanner scanner = new Scanner(System.in);

    UserInterface(){}

    public void display(){
        dealership = init();
        boolean didExit = false;
        while(!didExit){
            try{
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
            } catch (Exception e) {
                System.err.println("You entered a string or no input");
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    private Dealership init(){
        DealershipFileManager dealerManager = new DealershipFileManager();
        return dealerManager.getDealership();
    }

    public void processGetByPriceRequest(){
        try {
            System.out.println("What is the minimum price: ");
            double minPrice = scanner.nextDouble();
            System.out.println("What is the maximum price: ");
            double maxPrice = scanner.nextDouble();
            List<Vehicle> temp = dealership.getVehiclesByPrice(minPrice, maxPrice);
            if (temp.isEmpty()) System.out.println("No vehicles matched your query");
            else displayVehicles(temp);
        } catch (Exception e) {
            System.err.println("Your input is not a number");
            scanner.nextLine();
        }
    }

    public void processGetByMakeModelRequest(){
        System.out.println("Enter maker(Can leave empty): ");
        String make = scanner.nextLine();
        System.out.println("Enter model(Can leave empty): ");
        String model = scanner.nextLine();
        List<Vehicle> temp = dealership.getVehiclesByMakeModel(make, model);
        if (temp.isEmpty()) System.out.println("No vehicles matched your query");
        else displayVehicles(temp);
    }

    public void processGetByYearRequest(){
        try {
            System.out.println("Enter minimum year: ");
            int minYear = scanner.nextInt();
            System.out.println("Enter maximum year: ");
            int maxYear = scanner.nextInt();
            List<Vehicle> temp = dealership.getVehiclesByYear(minYear, maxYear);
            if (temp.isEmpty()) System.out.println("No vehicles matched your query");
            else displayVehicles(temp);
        } catch (Exception e) {
            System.err.println("Your input is not a number");
            scanner.nextLine();
        }
    }

    public void processGetByColorRequest(){
        System.out.println("Enter color you want to filter: ");
        String color = scanner.nextLine();
        List<Vehicle> temp = dealership.getVehiclesByColor(color);
        if (temp.isEmpty()) System.out.println("No vehicles matched your query");
        else displayVehicles(temp);
    }

    public void processGetByMileageRequest(){
        try {
            System.out.println("Enter your minimal mileage: ");
            int minMileage = scanner.nextInt();
            System.out.println("Enter your maximum mileage: ");
            int maxMileage = scanner.nextInt();
            scanner.nextLine();
            List<Vehicle> temp = dealership.getVehiclesByMileage(minMileage, maxMileage);
            if (temp.isEmpty()) System.out.println("No vehicles matched your query");
            else displayVehicles(temp);
        } catch (Exception e) {
            System.err.println("Your input is not a number");
            scanner.nextLine();
        }
    }

    public void processGetByVehicleTypeRequest(){
        System.out.println("Enter your preferred vehicle type: ");
        String vehicleType = scanner.nextLine();
        List<Vehicle> temp = dealership.getVehiclesByType(vehicleType);
        if (temp.isEmpty()) System.out.println("No vehicles matched your query");
        else displayVehicles(temp);
    }
    public void processGetAllVehiclesRequest(){
        List<Vehicle> temp = dealership.getAllVehicles();
        if (temp.isEmpty()) System.out.println("No vehicles are listed on the CSV");
        else displayVehicles(temp);
    }
    public void processAddVehicleRequest(){
        DealershipFileManager dfm = new DealershipFileManager();
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
            dfm.saveDealership(dealership);
            System.out.println("Vehicle added successfully");
        }
        catch(Exception ex){
            System.err.println("You have entered a string instead of an integer for vin, year, mileage, or price");
        }
    }
    public void processRemoveVehicleRequest(){
        DealershipFileManager dfm = new DealershipFileManager();
        System.out.println("Select a vehicle to remove from your inventory. Enter 0 if you want to exit.");
        int vin = scanner.nextInt();
        if(vin == 0) return;
        for(Vehicle vehicle : dealership.getAllVehicles()){
            if(vin == vehicle.getVin()){
                dealership.removeVehicle(vehicle);
                dfm.saveDealership(dealership);
                return;
            }
        }
        System.out.println("No vehicle exist in database.");
    }

    private void displayVehicles(List<Vehicle> vehicleList){
        if(vehicleList.isEmpty()) return;
        for(Vehicle vehicle : vehicleList){
            System.out.println(vehicle.toString());
        }
    }
}
