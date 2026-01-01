package com.nelkinda.training;

class Expense {
    private ExpenseType type;
    private int amount;

    public Expense(ExpenseType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

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
