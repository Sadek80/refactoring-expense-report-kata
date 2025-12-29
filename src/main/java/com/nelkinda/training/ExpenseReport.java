package com.nelkinda.training;

import java.util.Date;
import java.util.List;

class Expense {
    ExpenseType type;
    int amount;
}

public class ExpenseReport {
    public void printReport(List<Expense> expenses) {
        int mealExpenses = calculateMealExpenses(expenses);
        int total = calculateTotal(expenses);
        printReport(expenses, mealExpenses, total);
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
            System.out.println(getExpenseName(expense) + "\t" + expense.amount + "\t" +  getMealOverExpensesMarker(expense));
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

    private static String getExpenseName(Expense expense) {
        return switch (expense.type) {
            case DINNER -> "Dinner";
            case BREAKFAST -> "Breakfast";
            case CAR_RENTAL -> "Car Rental";
        };
    }
}
