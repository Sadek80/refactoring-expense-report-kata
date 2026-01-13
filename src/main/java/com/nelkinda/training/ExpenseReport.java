package com.nelkinda.training;

import java.util.ArrayList;
import java.util.List;

public class ExpenseReport {
    private final IClock clock;
    private final IPrinter printer;

    public ExpenseReport(IClock clock, IPrinter printer){
        this.clock = clock;
        this.printer = printer;
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
        printer.print("Expenses " + clock.getDate());

        for (ExpenseDetails expense : details.getExpenses()) {
            printer.print(expense.getName() + "\t" + expense.getAmount() + "\t" + getMealOverExpensesMarker(expense));
        }

        printer.print("Meal expenses: " + details.getMealExpenses());
        printer.print("Total expenses: " + details.getTotal());
    }

    private String getMealOverExpensesMarker(ExpenseDetails expenseDetails) {
        return expenseDetails.isMealOverExpense() ? "X" : " ";
    }

}
