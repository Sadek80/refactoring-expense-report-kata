package com.nelkinda.training;

import java.util.Collections;
import java.util.List;

public class ExpenseReportDetails {
    private final int total;
    private final int mealExpenses;
    private final List<ExpenseDetails> expenses;

    public ExpenseReportDetails(int total, int mealExpenses, List<ExpenseDetails> expenses){
        this.total = total;
        this.mealExpenses = mealExpenses;
        this.expenses = expenses;
    }

    public int getTotal() {
        return total;
    }

    public int getMealExpenses() {
        return mealExpenses;
    }

    public List<ExpenseDetails> getExpenses() {
        return Collections.unmodifiableList(expenses);
    }
}
