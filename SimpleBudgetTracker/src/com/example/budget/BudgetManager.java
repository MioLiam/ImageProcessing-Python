package com.example.budget;

import java.util.ArrayList;
import java.util.List;

public class BudgetManager {
    private final List<Transaction> transactions;

    public BudgetManager() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public double getTotalIncome() {
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalExpense() {
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getNetBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    public void printSummary() {
        System.out.println("========== Summary ==========");
        System.out.printf("Total Income : %.2f%n", getTotalIncome());
        System.out.printf("Total Expense: %.2f%n", getTotalExpense());
        System.out.printf("Net Balance  : %.2f%n", getNetBalance());
        System.out.println("=============================");
    }
}
