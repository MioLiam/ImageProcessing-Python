package com.example.budget;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StorageUtil {
    public static void saveTransactions(List<Transaction> transactions, String filePath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Path.of(filePath))) {
            for (Transaction t : transactions) {
                writer.write(t.toFileFormat());
                writer.newLine();
            }
            System.out.println("记录已成功保存到 " + filePath);
        } catch (IOException e) {
            System.err.println("保存记录失败: " + e.getMessage());
        }
    }

    public static void loadTransactions(BudgetManager manager, String filePath) {
        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            System.out.println("未找到交易记录文件，将创建新文件。");
            return;
        }
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Transaction transaction = Transaction.fromFileFormat(line);
                    manager.addTransaction(transaction);
                } catch (IllegalArgumentException ex) {
                    System.err.println("跳过无效记录: " + line);
                }
            }
            System.out.println("已从文件加载记录。");
        } catch (IOException e) {
            System.err.println("加载记录失败: " + e.getMessage());
        }
    }
}
