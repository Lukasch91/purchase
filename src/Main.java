import java.util.Scanner;


public class Main {
    public static void main(String args[]) {
        new Main().userInteraction();
    }

    public void userInteraction() {
        CashRegister cashRegister = new CashRegister();

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
                cashRegister.startPurchase();
                printToScreen("Enter code: ");
                String code = readInput();

                printToScreen("Number of items");
                Integer numberOfItems = Integer.parseInt(readInput());

                cashRegister.scanItem(Long.parseLong(code), numberOfItems);
            } else if (name.equals("2")) {
                printToScreen("Enter code: ");
                String code = readInput();
                printToScreen("Number of items");
                Integer numberOfItems = Integer.parseInt(readInput());
                cashRegister.scanItem(Long.parseLong(code), numberOfItems);
            } else if (name.equals("3")) {
                cashRegister.endPurchase();
            } else if (name.equals("4")) {
                cashRegister.withdrawals();
            } else if (name.equals("5")) {
                cashRegister.currentCashBalance();
            } else if (name.equals("6")) {
                keepRunning = false;
            } else if (name.equals("7")){
                ScannerWorker sw = new ScannerWorker();
                printToScreen("Number of items");
                Integer numberOfItems = Integer.parseInt(readInput());
                cashRegister.scanItem((long) sw.randomNumber, numberOfItems);
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