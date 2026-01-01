package com.nelkinda.training;

import java.util.ArrayList;
import java.util.List;

public class ExpenseReport {
    private IClock clock;

    public ExpenseReport(IClock clock){
        this.clock = clock;
    }

    public void printReport(List<Expense> expenses) {
        var reportDetails = getReportDetails(expenses);
        printReport(reportDetails);
    }

    private ExpenseReportDetails getReportDetails(List<Expense> expenses) {
        int total = 0;
        int mealExpenses = 0;
        List<ExpenseDetails> expenseDetails = new ArrayList<>();

        for (Expense expense : expenses) {
            if (expense.isMeal()) {
                mealExpenses += expense.getAmount();
            }

            total += expense.getAmount();

            expenseDetails.add(new ExpenseDetails(
                    expense.getAmount(),
                    expense.getExpenseName(),
                    expense.isOverExpense()
            ));
        }

        return new ExpenseReportDetails(total, mealExpenses, expenseDetails);
    }

    private void printReport(ExpenseReportDetails details) {
        System.out.println("Expenses " + clock.getDate());

        for (ExpenseDetails expense : details.getExpenses()) {
            System.out.println(expense.getName() + "\t" + expense.getAmount() + "\t" + getMealOverExpensesMarker(expense));
        }

        System.out.println("Meal expenses: " + details.getMealExpenses());
        System.out.println("Total expenses: " + details.getTotal());
    }

    private String getMealOverExpensesMarker(ExpenseDetails expenseDetails) {
        return expenseDetails.isMealOverExpense() ? "X" : " ";
    }

}
