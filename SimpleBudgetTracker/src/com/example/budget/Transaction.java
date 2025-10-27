package com.example.budget;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final double amount;
    private final TransactionType type;
    private final String description;
    private final LocalDateTime timestamp;

    public Transaction(double amount, TransactionType type, String description) {
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    public Transaction(double amount, TransactionType type, String description, LocalDateTime timestamp) {
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String format() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s | %s | %.2f | %s",
                formatter.format(timestamp), type, amount, description);
    }

    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return String.format("%s|%s|%.2f|%s",
                formatter.format(timestamp), type, amount, description.replace("|", "/"));
    }

    public static Transaction fromFileFormat(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid line: " + line);
        }
        LocalDateTime time = LocalDateTime.parse(parts[0]);
        TransactionType type = TransactionType.valueOf(parts[1]);
        double amount = Double.parseDouble(parts[2]);
        String description = parts[3];
        return new Transaction(amount, type, description, time);
    }
}
