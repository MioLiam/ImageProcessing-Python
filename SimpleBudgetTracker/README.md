# Simple Budget Tracker

一个适合初学者的 Java 命令行记账小项目，演示类、枚举、集合、文件读写和命令行交互等基础知识。

## 功能
- 添加收入或支出记录。
- 查看全部记录。
- 查看统计信息（总收入、总支出、净资产）。
- 将记录保存到本地文件并在程序启动时自动加载。

## 项目结构
```
SimpleBudgetTracker/
├── README.md
├── data/
│   └── transactions.txt
└── src/
    └── com/example/budget/
        ├── TransactionType.java
        ├── Transaction.java
        ├── BudgetManager.java
        ├── StorageUtil.java
        └── Main.java
```

## 运行步骤
1. 安装 JDK 11 或更高版本，并确保 `java` 与 `javac` 可在终端运行。
2. 在项目根目录执行以下命令编译：
   ```bash
   javac -d out src/com/example/budget/*.java
   ```
3. 运行程序：
   ```bash
   java -cp out com.example.budget.Main
   ```
4. 根据命令行提示进行操作。程序会自动读取和保存 `data/transactions.txt` 文件。

## VS Code 运行提示
- 安装 Extension Pack for Java。
- 打开 `Main.java` 并点击右上角的 “Run” 图标即可运行。
- 或者在 VS Code 终端中执行上述命令。

祝学习愉快！
