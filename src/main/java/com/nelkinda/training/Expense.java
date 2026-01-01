package com.nelkinda.training;

class Expense {
    private ExpenseType type;
    private int amount;

    public Expense(ExpenseType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    static boolean isMeal(Expense expense) {
        return expense.getType() == ExpenseType.DINNER || expense.getType() == ExpenseType.BREAKFAST;
    }

    public String getExpenseName() {
        return type.getName();
    }

    public int getAmount() {
        return amount;
    }

    public ExpenseType getType() {
        return type;
    }
}
