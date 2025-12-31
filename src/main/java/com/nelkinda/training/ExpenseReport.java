package com.nelkinda.training;

import java.util.Date;
import java.util.List;

public class ExpenseReport {
    public void printReport(List<Expense> expenses) {
        var reportDetails = getReportDetails(expenses);
        printReport(expenses, reportDetails);
    }

    private static ExpenseReportDetails getReportDetails(List<Expense> expenses) {
        int total = 0;
        int mealExpenses = 0;

        for (Expense expense : expenses) {
            if (expense.getType() == ExpenseType.DINNER || expense.getType() == ExpenseType.BREAKFAST) {
                mealExpenses += expense.getAmount();
            }

            total += expense.getAmount();
        }

        return new ExpenseReportDetails(total, mealExpenses);
    }

    private static void printReport(List<Expense> expenses, ExpenseReportDetails details) {
        System.out.println("Expenses " + new Date());

        for (Expense expense : expenses) {
            System.out.println(expense.getExpenseName() + "\t" + expense.getAmount() + "\t" + getMealOverExpensesMarker(expense));
        }

        System.out.println("Meal expenses: " + details.getMealExpenses());
        System.out.println("Total expenses: " + details.getTotal());
    }

    private static String getMealOverExpensesMarker(Expense expense) {
        return expense.type == ExpenseType.DINNER && expense.getAmount() > 5000 ||
                expense.type == ExpenseType.BREAKFAST && expense.getAmount() > 1000 ? "X" : " ";
    }
}
