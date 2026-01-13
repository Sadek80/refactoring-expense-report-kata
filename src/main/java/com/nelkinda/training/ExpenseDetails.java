package com.nelkinda.training;

public class ExpenseDetails {
    private final int amount;
    private final String name;
    private final boolean isMealOverExpense;

    public ExpenseDetails(int amount, String name, boolean isMealOverExpense) {
        this.amount = amount;
        this.name = name;
        this.isMealOverExpense = isMealOverExpense;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public boolean isMealOverExpense() {
        return isMealOverExpense;
    }
}
