package com.nelkinda.training;

class Expense {
    ExpenseType type;
    int amount;

    public String expenseName() {
        return type.getName();
    }

    public int getAmount() {
        return amount;
    }
}
