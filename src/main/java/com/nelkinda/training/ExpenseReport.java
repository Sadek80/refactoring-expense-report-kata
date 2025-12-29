package com.nelkinda.training;

import java.util.Date;
import java.util.List;

public class ExpenseReport {
    public void printReport(List<Expense> expenses) {
        var reportDetails = getReportDetails(expenses);
        printReport(expenses, reportDetails.getMealExpenses(), reportDetails.getTotal());
    }

    private static ExpenseReportDetails getReportDetails(List<Expense> expenses) {
        return new ExpenseReportDetails(calculateTotal(expenses), calculateMealExpenses(expenses));
    }

    private static int calculateMealExpenses(List<Expense> expenses) {
        int mealExpenses = 0;

        for (Expense expense : expenses) {
            mealExpenses = calculateMealExpense(expense, mealExpenses);
        }

        return mealExpenses;
    }

    private static int calculateTotal(List<Expense> expenses) {
        int total = 0;

        for (Expense expense : expenses) {
            total += expense.amount;
        }

        return total;
    }

    private static void printReport(List<Expense> expenses, int mealExpenses, int total) {
        System.out.println("Expenses " + new Date());

        for (Expense expense : expenses) {
            System.out.println(expense.expenseName() + "\t" + expense.amount + "\t" + getMealOverExpensesMarker(expense));
        }

        System.out.println("Meal expenses: " + mealExpenses);
        System.out.println("Total expenses: " + total);
    }

    private static int calculateMealExpense(Expense expense, int mealExpenses) {
        if (expense.type == ExpenseType.DINNER || expense.type == ExpenseType.BREAKFAST) {
            mealExpenses += expense.amount;
        }
        return mealExpenses;
    }

    private static String getMealOverExpensesMarker(Expense expense) {
        return expense.type == ExpenseType.DINNER && expense.amount > 5000 ||
                expense.type == ExpenseType.BREAKFAST && expense.amount > 1000 ? "X" : " ";
    }
}
