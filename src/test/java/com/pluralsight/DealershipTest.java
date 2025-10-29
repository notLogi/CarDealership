package com.pluralsight;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DealershipTest {
    @Test
    public void testGetVehiclesByPrice(){
        Dealership dealership = new Dealership("Test", "123 main St", "777-777-7777");
        dealership.addVehicle(new Vehicle(101, 2020, "Toyota", "Camry", "sedan", "blue", 30000, 18000));
        dealership.addVehicle(new Vehicle(102, 2021, "Honda", "Civic", "sedan", "red", 20000, 20000));
        dealership.addVehicle(new Vehicle(103, 2019, "Ford", "Escape", "SUV", "white", 40000, 22000));
        List<Vehicle> temp = dealership.getVehiclesByPrice(10000, 20000);
        assertEquals(2, temp.size());
    }
}