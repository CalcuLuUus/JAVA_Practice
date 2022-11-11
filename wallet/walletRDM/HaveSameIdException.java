package walletRDM;

public class HaveSameIdException extends Throwable {
    public HaveSameIdException() {
        System.out.println("The id has been existed");
    }
}
