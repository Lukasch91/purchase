package com.lukas;

import com.lukas.cashRegister.CashRegister;
import com.lukas.cashRegister.ManualScanner;
import com.lukas.cashRegister.RandomScanner;
import com.lukas.service.PurchaseService;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {

    private PurchaseService PurchaseService;
    private CashRegister cashRegister;

    public Main(PurchaseService PurchaseService, CashRegister cashRegister) {
        this.PurchaseService = PurchaseService;
        this.cashRegister = cashRegister;
    }

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
        //--manual
        String arg = "--default";
        if (args.length > 0) {
            arg = args[0];
        }
        PurchaseService PurchaseService = new PurchaseService();
        new Main(PurchaseService, new CashRegister(PurchaseService)).userInteraction(arg);
    }

    public void userInteraction(String mode) throws SQLException, ClassNotFoundException {

        if (mode.equals("--manual")) {
            cashRegister.setScanner(new ManualScanner());
        } else {
            cashRegister.setScanner(new RandomScanner());
        }


        boolean keepRunning = true;
        while (keepRunning) {

            printToScreen("1 - Start purchase    \n" +
                    "2 - Scan item         \n" +
                    "3 - End purchase      \n" +
                    "4 - WithDraw      \n" +
                    "5 - Current cash register balance     \n" +
                    "6 - Get cash register balance by date \n" +
                    "7 - Exit                ");
            String name = readInput();

            if (name.equals("1")) {
                if (mode.equals("--manual")) {
                    cashRegister.startPurchase();
                    printToScreen("Enter code: ");
                    String code = readInput();

                    printToScreen("Number of items");
                    Integer numberOfItems = Integer.parseInt(readInput());
                    Long itemCode = Long.parseLong(code);

                    cashRegister.scanItem(itemCode, numberOfItems);
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
                System.out.println("Withdrawal sum:");
                PurchaseService.addWithdrawal(readInputasDouble());
            } else if (name.equals("5")) {
                Double balance = PurchaseService.getCurrentBalance();
                System.out.println("Current cash register balance :" + balance);
            } else if (name.equals("6")) {
                System.out.println("Enter date:");
                String date = readInput();
                Double balance = PurchaseService.getBalanceByDate(date);
                System.out.println("Cash register balance on " + date + " was : " + balance);
            } else if (name.equals("7")) {
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

    private Double readInputasDouble() {
        Scanner input = new Scanner(System.in);
        return input.nextDouble();
    }
}