package com.nelkinda.training;

class Expense {
    ExpenseType type;
    int amount;

    public String getExpenseName() {
        return type.getName();
    }

    public int getAmount() {
        return amount;
    }

    public ExpenseType getType() {
        return type;
    }
}
