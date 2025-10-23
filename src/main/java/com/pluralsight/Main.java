package com.pluralsight;

public class Main {
    public static void main(String[] args) {
        UserInterface UI = new UserInterface();
        UI.display();
        DealershipFileManager dfm = new DealershipFileManager();
        Dealership dealer = dfm.getDealership();
    }
}
