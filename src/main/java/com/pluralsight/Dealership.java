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

    }
    public List<Vehicle> getVehiclesByMakeModel(String make, String model){

    }
    public List<Vehicle> getVehiclesByYear(int min, int max){

    }
    public List<Vehicle> getVehiclesByColor(String color){

    }
    public List<Vehicle> getVehiclesByMileage(double min, double max){

    }
    public List<Vehicle> getVehiclesByType(String vehicleType){

    }
    public List<Vehicle> getAllVehicles(){

    }
    public void addVehicle(Vehicle vehicle){

    }
    public void removeVehicle(Vehicle vehicle){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Vehicle> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Vehicle> inventory) {
        this.inventory = inventory;
    }
}
