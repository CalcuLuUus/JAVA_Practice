package walletADM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class testWallet {
    private static ArrayList<VirtualWalletBo> wallets;
    private static VirtualWalletService virtualWalletService;

    public static void main(String[] args) throws NoneOfWalletException {
        virtualWalletService = new VirtualWalletService();
        wallets = new ArrayList<VirtualWalletBo>();

        intro();
        Scanner scanner = new Scanner(System.in);
        int op = 0;
        do{
            menu();
            op = scanner.nextInt();
            if(op == 1){
                create();
            }else if(op == 2){
                getbalance();
            }else if(op == 3){
                debit();
            }else if(op == 4){
                credit();
            }else if(op == 5){
                transfer();
            }
            System.out.println();
        }while(op != 6);
    }

    private static void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input source wallet id:");
        Long fromId = sc.nextLong();
        System.out.println("Please input target wallet id:");
        Long toId = sc.nextLong();
        System.out.println("How much would you like to transfer?");
        BigDecimal amount = sc.nextBigDecimal();
        virtualWalletService.transfer(fromId, toId, amount, wallets);
    }

    private static void credit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input your wallet id:");
        Long id = sc.nextLong();
        System.out.println("How much would you like to credit?");
        BigDecimal amount = sc.nextBigDecimal();
        virtualWalletService.credit(id, amount, wallets);
    }

    private static void debit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input your wallet id:");
        Long id = sc.nextLong();
        System.out.println("How much would you like to debit?");
        BigDecimal amount = sc.nextBigDecimal();
        virtualWalletService.debit(id, amount, wallets);
    }

    private static void getbalance() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input your wallet id:");
        Long id = sc.nextLong();
        BigDecimal ret = virtualWalletService.getBalance(id, wallets);
        if(ret != null){
            System.out.println("Wallet id: " + id + "\tbalance: " + ret);
        }
    }

    private static void create() {
        Scanner sc = new Scanner(System.in);
        Long id = (long) (wallets.size() + 1);
        Date date = new Date();
        Long Time = date.getTime();
        System.out.println("Please input your balance: ");
        BigDecimal balance = sc.nextBigDecimal();

        virtualWalletService.createVirtualWallet(id, Time, balance, wallets);
    }

    private static void menu() {
        System.out.println("Please select the service you need");
        System.out.println("1. create a new wallet");
        System.out.println("2. get balance");
        System.out.println("3. debit");
        System.out.println("4. credit");
        System.out.println("5. transfer");
        System.out.println("6. exit");
    }

    private static void intro() {
        System.out.println("Welcome");
    }
}
