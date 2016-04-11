import java.util.Scanner;


public class Main {
    public static void main(String args[]) {
        //--manual
        String arg = "--default";
        if (args.length > 0) {
            arg = args[0];
        }
        new Main().userInteraction(arg);
    }

    public void userInteraction(String mode) {

        CashRegister cashRegister;
        if (mode.equals("--manual")) {
            cashRegister = new CashRegister(new ManualScanner());
        } else {
            cashRegister = new CashRegister(new RandomScanner());
        }


        boolean keepRunning = true;
        while (keepRunning) {

            printToScreen("1 - Start purchase    \n" +
                    "2 - Scan item         \n" +
                    "3 - End purchase      \n" +
                    "4 - WithDraw      \n" +
                    "5 - Current cash register balance     \n" +
                    "6 - Exit                ");
            String name = readInput();

            if (name.equals("1")) {
                if (mode.equals("--manual")) {
                    cashRegister.startPurchase();
                    printToScreen("Enter code: ");
                    String code = readInput();

                    printToScreen("Number of items");
                    Integer numberOfItems = Integer.parseInt(readInput());

                    cashRegister.scanItem(Long.parseLong(code), numberOfItems);
                } else {
                    cashRegister.startPurchase();
                    printToScreen("Number of items");

                    Integer numberOfItems = Integer.parseInt(readInput());
                    cashRegister.scanItem(numberOfItems);
                }

            } else if (name.equals("2")) {
                if (mode.equals("--manual")) {
                    printToScreen("Enter code: ");
                    String code = readInput();
                    printToScreen("Number of items");
                    Integer numberOfItems = Integer.parseInt(readInput());
                    cashRegister.scanItem(Long.parseLong(code), numberOfItems);
                } else

                    printToScreen("Number of items");
                    Integer numberOfItems = Integer.parseInt(readInput());
                    cashRegister.scanItem(numberOfItems);

            } else if (name.equals("3")) {
                cashRegister.endPurchase();
            } else if (name.equals("4")) {
                cashRegister.withdrawals();
            } else if (name.equals("5")) {
                cashRegister.currentCashBalance();
            } else if (name.equals("6")) {
                keepRunning = false;
            }
        }

    }


    private void printToScreen(String result) {
        System.out.println(result);
    }

    private String readInput() {
        Scanner input = new Scanner(System.in);
        return input.next();
    }
}