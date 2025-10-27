package com.example.budget;

import java.util.Scanner;

public class Main {
    private static final String DATA_FILE = "data/transactions.txt";

    public static void main(String[] args) {
        BudgetManager manager = new BudgetManager();
        StorageUtil.loadTransactions(manager, DATA_FILE);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("请选择操作：");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addTransaction(manager, scanner, TransactionType.INCOME);
                    break;
                case "2":
                    addTransaction(manager, scanner, TransactionType.EXPENSE);
                    break;
                case "3":
                    printTransactions(manager);
                    break;
                case "4":
                    manager.printSummary();
                    break;
                case "5":
                    StorageUtil.saveTransactions(manager.getAllTransactions(), DATA_FILE);
                    break;
                case "0":
                    running = false;
                    StorageUtil.saveTransactions(manager.getAllTransactions(), DATA_FILE);
                    break;
                default:
                    System.out.println("无效选择，请重新输入。");
            }
        }

        scanner.close();
        System.out.println("谢谢使用记账系统，已退出。");
    }

    private static void printMenu() {
        System.out.println("\n===== 简易记账系统 =====");
        System.out.println("1. 记录收入");
        System.out.println("2. 记录支出");
        System.out.println("3. 查看全部记录");
        System.out.println("4. 查看统计信息");
        System.out.println("5. 手动保存数据");
        System.out.println("0. 退出程序");
        System.out.println("=======================");
    }

    private static void addTransaction(BudgetManager manager, Scanner scanner, TransactionType type) {
        System.out.print("请输入金额：");
        String amountInput = scanner.nextLine().trim();
        double amount;
        try {
            amount = Double.parseDouble(amountInput);
        } catch (NumberFormatException e) {
            System.out.println("金额格式不正确，请重新输入。");
            return;
        }

        if (amount <= 0) {
            System.out.println("金额必须大于0。");
            return;
        }

        System.out.print("请输入描述：");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) {
            description = type == TransactionType.INCOME ? "收入" : "支出";
        }

        Transaction transaction = new Transaction(amount, type, description);
        manager.addTransaction(transaction);
        System.out.println("成功添加记录：" + transaction.format());
    }

    private static void printTransactions(BudgetManager manager) {
        System.out.println("\n===== 当前记录 =====");
        if (manager.getAllTransactions().isEmpty()) {
            System.out.println("暂无记录。");
        } else {
            manager.getAllTransactions().forEach(t -> System.out.println(t.format()));
        }
    }
}
