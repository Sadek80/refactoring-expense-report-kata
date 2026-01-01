package com.nelkinda.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ExpenseReportTest {

    private final ZoneId zone = ZoneId.of("Africa/Cairo");
    private final Instant instant = LocalDate.of(2025, 12, 31).atStartOfDay(zone).toInstant();
    private final Date lastDay2025Date = Date.from(instant);

    @Spy
    private IClock clock = new ClockStub(lastDay2025Date);
    @Mock
    private IPrinter printer;

    @InjectMocks
    private ExpenseReport expenseReport;

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
            var expenseReport = new ExpenseReport(clock, new Printer());
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
