package com.nelkinda.training;

enum ExpenseType {
    DINNER("Dinner", true),
    BREAKFAST("Breakfast", true),
    CAR_RENTAL("Car Rental", false),
    LAUNCH("Launch", true);

    private final String name;
    private final boolean isMeal;

    ExpenseType(String name, boolean isMeal) {
        this.name = name;
        this.isMeal = isMeal;
    }

    public String getName() {
        return name;
    }

    public boolean isMeal() {
        return isMeal;
    }
}
