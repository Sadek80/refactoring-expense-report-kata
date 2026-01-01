package com.nelkinda.training;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

class ExpenseReportTest {

    ZoneId zone = ZoneId.of("Africa/Cairo");
    Instant instant = LocalDate.of(2025, 12, 31).atStartOfDay(zone).toInstant();
    Date lastDay2025Date = Date.from(instant);

    private final IClock clock = new ClockStub(lastDay2025Date);
    private final ExpenseReport expenseReport = new ExpenseReport(clock);

    @Test
    void printReport_characterization() {
        // Arrange
        PrintStream originalOut = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        try {
            var dinnerExpenseBelowLimit = new Expense(ExpenseType.DINNER, 500);

            var dinnerExpenseExceedsLimit = new Expense(ExpenseType.DINNER, 5001);

            var breakfastExpenseBelowLimit = new Expense(ExpenseType.BREAKFAST, 500);

            var breakfastExpenseExceedsLimit = new Expense(ExpenseType.BREAKFAST, 5001);

            var carRentalExpense = new Expense(ExpenseType.CAR_RENTAL, 50);

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
                    """, clock.getDate()));

        } finally {
            System.setOut(originalOut);
        }
    }
}
