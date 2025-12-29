package com.nelkinda.training;

enum ExpenseType {
    DINNER("Dinner"),
    BREAKFAST("Breakfast"),
    CAR_RENTAL("Car Rental");

    private final String name;

    ExpenseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
