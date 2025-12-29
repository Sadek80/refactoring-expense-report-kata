package com.nelkinda.training;

import java.util.Date;
import java.util.List;

enum ExpenseType {
    DINNER, BREAKFAST, CAR_RENTAL
}

class Expense {
    ExpenseType type;
    int amount;
}

public class ExpenseReport {
    public void printReport(List<Expense> expenses) {
        int total = 0;
        int mealExpenses = 0;

        for (Expense expense : expenses) {
            mealExpenses = calculateMealExpense(expense, mealExpenses);
            total += expense.amount;
        }

        System.out.println("Expenses " + new Date());

        for (Expense expense : expenses) {
            printExpenseContribution(expense);
        }

        System.out.println("Meal expenses: " + mealExpenses);
        System.out.println("Total expenses: " + total);
    }

    private static void printExpenseContribution(Expense expense) {
        System.out.println(getExpenseName(expense) + "\t" + expense.amount + "\t" +  getMealOverExpensesMarker(expense));
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
