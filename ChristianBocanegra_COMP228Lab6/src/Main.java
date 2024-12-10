import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(2000);
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(account, 250, true));
        transactions.add(new Transaction(account, 50, false));
        transactions.add(new Transaction(account, 175, true));
        transactions.add(new Transaction(account, 300, false));
        transactions.add(new Transaction(account, 500, true));

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (Transaction transaction : transactions) {
            executorService.execute(transaction);
        }
        executorService.shutdown();
    }
}
