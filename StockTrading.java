import java.util.*;
public class StockTrading {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        HashMap<String, Double> stocks = new HashMap<>();
        stocks.put("AAPL", 150.0);
        stocks.put("GOOGL", 2800.0);
        stocks.put("TSLA", 700.0);
        stocks.put("AMZN", 3500.0);

        HashMap<String, Integer> portfolio = new HashMap<>();
        System.out.print("Enter the amount you have to invest in stocks : ");
        double balance = sc.nextDouble(); 

        while (true) {
            System.out.println("\nStock Market Prices:");
            for (String stock : stocks.keySet()) {
                System.out.println(stock + " - $" + stocks.get(stock));
            }
            System.out.println("\n Balance: $" + balance);
            System.out.println("1 Buy Stock | 2 Sell Stock | 3 View Portfolio | 4 Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            if (choice == 1) {  
                System.out.print("Enter stock symbol (AAPL, GOOGL, TSLA, AMZN): ");
                String stock = sc.next().toUpperCase();
                if (!stocks.containsKey(stock)) {
                    System.out.println(" Invalid stock.");
                    continue;
                }
                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();
                double cost = stocks.get(stock) * qty;
                if (cost > balance) {
                    System.out.println(" Not enough balance.");
                } else {
                    balance -= cost;
                    portfolio.put(stock, portfolio.getOrDefault(stock, 0) + qty);
                    System.out.println(" Bought " + qty + " shares of " + stock);
                }
            } else if (choice == 2) {  
                System.out.print("Enter stock symbol to sell: ");
                String stock = sc.next().toUpperCase();
                if (!portfolio.containsKey(stock)) {
                    System.out.println(" You don't own this stock.");
                    continue;
                }
                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();
                if (portfolio.get(stock) < qty) {
                    System.out.println(" Not enough shares.");
                } else {
                    double earnings = stocks.get(stock) * qty;
                    balance += earnings;
                    portfolio.put(stock, portfolio.get(stock) - qty);
                    if (portfolio.get(stock) == 0) portfolio.remove(stock);
                    System.out.println(" Sold " + qty + " shares of " + stock);
                }
            } else if (choice == 3) {  
                System.out.println("\n Your Portfolio:");
                if (portfolio.isEmpty()) {
                    System.out.println("No stocks owned.");
                } else {
                    for (String stock : portfolio.keySet()) {
                        System.out.println(stock + ": " + portfolio.get(stock) + " shares");
                    }
                }
            } else if (choice == 4) {  
                System.out.println(" Exiting, Goodbye!");
                break;
            } else {
                System.out.println(" Invalid choice.");
            }
        }
        sc.close();
    }
}
