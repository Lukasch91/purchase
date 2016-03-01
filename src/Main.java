import java.io.Console;
import java.util.Scanner;


public class Main {
    public static void main(String args[]) {


        CashRegister cashRegister = new CashRegister();


        Console console = System.console();
        if (console == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        boolean keepRunning = true;
        while (keepRunning) {
            String name = console.readLine("1 - Start purchase    \n" +
                    "2 - Scan item         \n" +
                    "3 - End purchase      \n" +
                    "4 - Exit                ");

            if (name.equals("1")) {
                cashRegister.startPurchase();
                String code = console.readLine("Enter code: ");
                Scanner input = new Scanner(System.in);

                System.out.println("Number of items");
                Integer numberOfItems = Integer.parseInt(input.next());
                cashRegister.scan(Long.parseLong(code), numberOfItems);
            } else if (name.equals("2")) {
                String code = console.readLine("Enter code: ");
                Scanner input = new Scanner(System.in);
                System.out.println("Number of items");
                Integer numberOfItems = Integer.parseInt(input.next());
                cashRegister.scan(Long.parseLong(code), numberOfItems);
            } else if (name.equals("3")) {
                cashRegister.printBill();
            } else if (name.equals("4")) {
                keepRunning = false;
            }

        }
    }
}