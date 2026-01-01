package com.nelkinda.training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenseReport {
    public void printReport(List<Expense> expenses) {
        var reportDetails = getReportDetails(expenses);
        printReport(reportDetails);
    }

    private static ExpenseReportDetails getReportDetails(List<Expense> expenses) {
        int total = 0;
        int mealExpenses = 0;
        List<ExpenseDetails> expenseDetails = new ArrayList<>();

        for (Expense expense : expenses) {
            if (expense.getType() == ExpenseType.DINNER || expense.getType() == ExpenseType.BREAKFAST) {
                mealExpenses += expense.getAmount();
            }

            total += expense.getAmount();

            expenseDetails.add(new ExpenseDetails(
                    expense.getAmount(),
                    expense.getExpenseName(),
                    isOverExpense(expense)
            ));
        }

        return new ExpenseReportDetails(total, mealExpenses, expenseDetails);
    }

    private static void printReport(ExpenseReportDetails details) {
        System.out.println("Expenses " + new Date());

        for (ExpenseDetails expense : details.getExpenses()) {
            System.out.println(expense.getName() + "\t" + expense.getAmount() + "\t" + getMealOverExpensesMarker(expense));
        }

        System.out.println("Meal expenses: " + details.getMealExpenses());
        System.out.println("Total expenses: " + details.getTotal());
    }

    private static String getMealOverExpensesMarker(ExpenseDetails expenseDetails) {
        return expenseDetails.isMealOverExpense() ? "X" : " ";
    }

    private static boolean isOverExpense(Expense expense) {
        return expense.getType() == ExpenseType.DINNER && expense.getAmount() > 5000 ||
                expense.getType() == ExpenseType.BREAKFAST && expense.getAmount() > 1000;
    }
}
