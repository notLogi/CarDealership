package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone){
        inventory = new ArrayList<>();
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max){
        return filteredListHelper(vehicle -> vehicle.getPrice() >= min && vehicle.getPrice() <= max);
    }
    public List<Vehicle> getVehiclesByMakeModel(String make, String model){
        if(make.isEmpty() && model.isEmpty()) return new ArrayList<>();
        if(!make.isEmpty() && !model.isEmpty()){
            return filteredListHelper(vehicle -> vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model));
        }
        else{
            return filteredListHelper(vehicle -> vehicle.getMake().equalsIgnoreCase(make) || vehicle.getModel().equalsIgnoreCase(model));
        }
    }
    public List<Vehicle> getVehiclesByYear(int min, int max){
        return filteredListHelper(vehicle -> vehicle.getYear() >= min && vehicle.getYear() <= max);
    }
    public List<Vehicle> getVehiclesByColor(String color){
        if(color == null || color.isEmpty()) return new ArrayList<>();
        return filteredListHelper(vehicle -> vehicle.getColor().equalsIgnoreCase((color)));
    }
    public List<Vehicle> getVehiclesByMileage(double min, double max){
        return filteredListHelper(vehicle -> vehicle.getOdometer() >= min && vehicle.getOdometer() <= max);
    }
    public List<Vehicle> getVehiclesByType(String vehicleType){
        if(vehicleType == null || vehicleType.isEmpty()) return new ArrayList<>();
        return filteredListHelper(vehicle -> vehicle.getVehicleType().equalsIgnoreCase((vehicleType)));
    }

    public List<Vehicle> filteredListHelper(Predicate<Vehicle> predicate){
        ArrayList<Vehicle> filteredList = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(predicate.test(vehicle)){
                filteredList.add(vehicle);
            }
        }
        return filteredList.isEmpty() ? new ArrayList<>() : filteredList;
    }

    public List<Vehicle> getAllVehicles(){
        return inventory;
    }
    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }
    public void removeVehicle(Vehicle vehicle){
        inventory.remove(vehicle);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
