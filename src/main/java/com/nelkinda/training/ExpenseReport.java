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
            if (expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST) {
                mealExpenses += expense.amount;
            }

            total += expense.amount;
        }

        return new ExpenseReportDetails(total, mealExpenses);
    }

    private static void printReport(List<Expense> expenses, ExpenseReportDetails details) {
        System.out.println("Expenses " + new Date());

        for (Expense expense : expenses) {
            System.out.println(expense.expenseName() + "\t" + expense.amount + "\t" + getMealOverExpensesMarker(expense));
        }

        System.out.println("Meal expenses: " + details.getMealExpenses());
        System.out.println("Total expenses: " + details.getTotal());
    }

    private static String getMealOverExpensesMarker(Expense expense) {
        return expense.type == ExpenseType.DINNER && expense.amount > 5000 ||
                expense.type == ExpenseType.BREAKFAST && expense.amount > 1000 ? "X" : " ";
    }
}
