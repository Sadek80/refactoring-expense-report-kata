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
