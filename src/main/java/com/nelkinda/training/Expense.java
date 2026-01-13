package com.nelkinda.training;

class Expense {
    private ExpenseType type;
    private int amount;

    public Expense(ExpenseType type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    // We can add the limits to the enum too
    public boolean isOverExpense() {
        return this.type == ExpenseType.DINNER && this.amount > 5000 ||
                this.type == ExpenseType.BREAKFAST && this.amount > 1000 ||
                    this.type == ExpenseType.LAUNCH && this.amount > 2000;
    }

    public boolean isMeal() {
        return type.isMeal();
    }

    public String getExpenseName() {
        return type.getName();
    }

    public int getAmount() {
        return amount;
    }

}
