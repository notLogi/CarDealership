package com.pluralsight;

import java.io.*;

public class DealershipFileManager {

    private static final String FILE_NAME = "dealership.csv";

    public Dealership getDealership() {
        String name, address, phone;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String input = reader.readLine();
            String[] splitInfo = input.split("\\|");
            name = splitInfo[0];
            address = splitInfo[1];
            phone = splitInfo[2];
            Dealership dealership = new Dealership(name, address, phone);
            while ((input = reader.readLine()) != null) {
                splitInfo = input.split("\\|");
                int vin = Integer.parseInt(splitInfo[0]);
                int year = Integer.parseInt(splitInfo[1]);
                String make = splitInfo[2];
                String model = splitInfo[3];
                String vehicleType = splitInfo[4];
                String color = splitInfo[5];
                int odometer = Integer.parseInt(splitInfo[6]);
                double price = Double.parseDouble(splitInfo[7]);
                dealership.addVehicle(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
            }
            //for(Vehicle vehicle : dealership.getInventory()){
            //    System.out.println(vehicle.toString());
            //}
            return dealership;
        } catch (Exception e) {
            System.err.println("CSV can't be found!!!");
            return null;
        }
    }
    public void saveDealership(Dealership dealership){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone() + "\n");
            for(Vehicle vehicle : dealership.getAllVehicles()){
                writer.write(vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|" + vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer() + "|" + String.format("%.2f", vehicle.getPrice()) + "\n");
            }
            writer.flush();
            System.out.println("Overwrite successful!");
        } catch (Exception e) {
            System.err.println("Failed to write to csv file");
        }
    }
}
