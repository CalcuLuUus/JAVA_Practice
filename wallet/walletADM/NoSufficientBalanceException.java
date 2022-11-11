package walletADM;

public class NoSufficientBalanceException extends Throwable {
    public NoSufficientBalanceException() {
        System.out.println("No Sufficient Balance");
    }
}
