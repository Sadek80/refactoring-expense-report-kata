package com.nelkinda.training;

public class ExpenseReportDetails {
    private final int total;
    private final int mealExpenses;

    public ExpenseReportDetails(int total, int mealExpenses){
        this.total = total;
        this.mealExpenses = mealExpenses;
    }

    public int getTotal() {
        return total;
    }

    public int getMealExpenses() {
        return mealExpenses;
    }
}
