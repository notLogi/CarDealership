package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

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
        ArrayList<Vehicle> filteredList = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getPrice() >= min && vehicle.getPrice() <= max){
                filteredList.add(vehicle);
            }
        }
        if(filteredList.isEmpty()) return null;
        return filteredList;
    }
    public List<Vehicle> getVehiclesByMakeModel(String make, String model){
        if(make.isEmpty() && model.isEmpty()) return null;
        ArrayList<Vehicle> filteredList = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(!make.isEmpty() && !model.isEmpty()){
                if(vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)){
                    filteredList.add(vehicle);
                }
            }
            else if(vehicle.getMake().equalsIgnoreCase(make) || vehicle.getModel().equalsIgnoreCase(model)){
                filteredList.add(vehicle);
            }
        }
        if(filteredList.isEmpty()) return null;
        return filteredList;
    }
    public List<Vehicle> getVehiclesByYear(int min, int max){
        ArrayList<Vehicle> filteredList = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getYear() >= min && vehicle.getYear() <= max){
                filteredList.add(vehicle);
            }
        }
        return filteredList;
    }
    public List<Vehicle> getVehiclesByColor(String color){
        ArrayList<Vehicle> filteredList = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getColor().equalsIgnoreCase(color)){
                filteredList.add(vehicle);
            }
        }
        if(filteredList.isEmpty()) return null;
        return filteredList;
    }
    public List<Vehicle> getVehiclesByMileage(double min, double max){
        ArrayList<Vehicle> filteredList = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getOdometer() >= min && vehicle.getOdometer() <= max){
                filteredList.add(vehicle);
            }
        }
        if(filteredList.isEmpty()) return null;
        return filteredList;
    }
    public List<Vehicle> getVehiclesByType(String vehicleType){
        if(vehicleType.isEmpty()) return null;
        ArrayList<Vehicle> filteredList = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getVehicleType().equalsIgnoreCase(vehicleType)){
                filteredList.add(vehicle);
            }
        }
        if(filteredList.isEmpty()) return null;
        return filteredList;
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
}
