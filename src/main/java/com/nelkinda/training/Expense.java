package com.nelkinda.training;

class Expense {
    private ExpenseType type;
    private int amount;
    private boolean isMeal;
    
    public Expense(ExpenseType type, int amount) {
        this.type = type;
        this.amount = amount;
        this.isMeal = type == ExpenseType.DINNER || type == ExpenseType.BREAKFAST;
    }

    static boolean isOverExpense(Expense expense) {
        return expense.getType() == ExpenseType.DINNER && expense.getAmount() > 5000 ||
                expense.getType() == ExpenseType.BREAKFAST && expense.getAmount() > 1000;
    }

    public boolean isMeal() {
        return isMeal;
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
