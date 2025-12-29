package com.nelkinda.training;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.List;


class ExpenseReportTest {

    private final ExpenseReport expenseReport = new ExpenseReport();

    @Test
    void printReport_characterization() {
        // Arrange
        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        try {
            var dinnerExpenseBelowLimit = new Expense();
            dinnerExpenseBelowLimit.type = ExpenseType.DINNER;
            dinnerExpenseBelowLimit.amount = 500;

            var dinnerExpenseExceedsLimit = new Expense();
            dinnerExpenseExceedsLimit.type = ExpenseType.DINNER;
            dinnerExpenseExceedsLimit.amount = 5001;

            var breakfastExpenseBelowLimit = new Expense();
            breakfastExpenseBelowLimit.type = ExpenseType.BREAKFAST;
            breakfastExpenseBelowLimit.amount = 500;

            var breakfastExpenseExceedsLimit = new Expense();
            breakfastExpenseExceedsLimit.type = ExpenseType.BREAKFAST;
            breakfastExpenseExceedsLimit.amount = 5001;

            var carRentalExpense = new Expense();
            carRentalExpense.type = ExpenseType.CAR_RENTAL;
            carRentalExpense.amount = 50;


            var expensesList = List.of(dinnerExpenseBelowLimit,
                    dinnerExpenseExceedsLimit,
                    breakfastExpenseBelowLimit,
                    breakfastExpenseExceedsLimit,
                    carRentalExpense);

            // Act
            expenseReport.printReport(expensesList);

            // Assert
            String printed = out.toString();

            assertEquals(printed, String.format("""
                    Expenses %s
                    Dinner	500	\s
                    Dinner	5001	X
                    Breakfast	500	\s
                    Breakfast	5001	X
                    Car Rental	50	\s
                    Meal expenses: 11002
                    Total expenses: 11052
                    """, new Date()));

        } finally {
            System.setOut(originalOut);
        }
    }
}
