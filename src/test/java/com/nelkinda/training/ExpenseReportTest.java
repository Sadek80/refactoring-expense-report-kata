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

            var launchExpenseBelowLimit = new Expense(ExpenseType.LAUNCH, 500);

            var launchExpenseExceedsLimit = new Expense(ExpenseType.LAUNCH, 2001);

            var carRentalExpense = new Expense(ExpenseType.CAR_RENTAL, 50);

            var expensesList = List.of(dinnerExpenseBelowLimit,
                    dinnerExpenseExceedsLimit,
                    breakfastExpenseBelowLimit,
                    breakfastExpenseExceedsLimit,
                    carRentalExpense,
                    launchExpenseBelowLimit,
                    launchExpenseExceedsLimit);

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
                    Launch	500	\s
                    Launch	2001	X
                    Meal expenses: 13503
                    Total expenses: 13553
                    """, clock.getDate()));

        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    void printReport_dinnerBelowLimit() {
        expenseReport.printReport(List.of(new Expense(ExpenseType.DINNER, 500)));

        InOrder inOrder = inOrder(printer);
        inOrder.verify(printer).print("Expenses " + clock.getDate());
        inOrder.verify(printer).print("Dinner\t500\t ");
        inOrder.verify(printer).print("Meal expenses: 500");
        inOrder.verify(printer).print("Total expenses: 500");

        verifyNoMoreInteractions(printer);
    }

    @Test
    void printReport_dinnerOverLimitMarksX() {
        expenseReport.printReport(List.of(new Expense(ExpenseType.DINNER, 5001)));

        InOrder inOrder = inOrder(printer);
        inOrder.verify(printer).print("Expenses " + clock.getDate());
        inOrder.verify(printer).print("Dinner\t5001\tX");
        inOrder.verify(printer).print("Meal expenses: 5001");
        inOrder.verify(printer).print("Total expenses: 5001");

        verifyNoMoreInteractions(printer);
    }

    @Test
    void printReport_breakfastBelowLimit() {
        expenseReport.printReport(List.of(new Expense(ExpenseType.BREAKFAST, 500)));

        InOrder inOrder = inOrder(printer);
        inOrder.verify(printer).print("Expenses " + clock.getDate());
        inOrder.verify(printer).print("Breakfast\t500\t ");
        inOrder.verify(printer).print("Meal expenses: 500");
        inOrder.verify(printer).print("Total expenses: 500");

        verifyNoMoreInteractions(printer);
    }

    @Test
    void printReport_breakfastOverLimitMarksX() {
        expenseReport.printReport(List.of(new Expense(ExpenseType.BREAKFAST, 5001)));

        InOrder inOrder = inOrder(printer);
        inOrder.verify(printer).print("Expenses " + clock.getDate());
        inOrder.verify(printer).print("Breakfast\t5001\tX");
        inOrder.verify(printer).print("Meal expenses: 5001");
        inOrder.verify(printer).print("Total expenses: 5001");

        verifyNoMoreInteractions(printer);
    }

    @Test
    void printReport_carRental() {
        expenseReport.printReport(List.of(new Expense(ExpenseType.CAR_RENTAL, 50)));

        InOrder inOrder = inOrder(printer);
        inOrder.verify(printer).print("Expenses " + clock.getDate());
        inOrder.verify(printer).print("Car Rental\t50\t ");
        inOrder.verify(printer).print("Meal expenses: 0");
        inOrder.verify(printer).print("Total expenses: 50");

        verifyNoMoreInteractions(printer);
    }

    @Test
    void printReport_launchBelowLimit() {
        expenseReport.printReport(List.of(new Expense(ExpenseType.LAUNCH, 500)));

        InOrder inOrder = inOrder(printer);
        inOrder.verify(printer).print("Expenses " + clock.getDate());
        inOrder.verify(printer).print("Launch\t500\t ");
        inOrder.verify(printer).print("Meal expenses: 500");
        inOrder.verify(printer).print("Total expenses: 500");

        verifyNoMoreInteractions(printer);
    }

    @Test
    void printReport_launchOverLimitMarksX() {
        expenseReport.printReport(List.of(new Expense(ExpenseType.LAUNCH, 2001)));

        InOrder inOrder = inOrder(printer);
        inOrder.verify(printer).print("Expenses " + clock.getDate());
        inOrder.verify(printer).print("Launch\t2001\tX");
        inOrder.verify(printer).print("Meal expenses: 2001");
        inOrder.verify(printer).print("Total expenses: 2001");

        verifyNoMoreInteractions(printer);
    }
}
