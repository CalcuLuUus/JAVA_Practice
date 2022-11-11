package walletRDM;

import java.math.BigDecimal;
import java.util.Date;

public class VirtualWalletBo {
    private Long id;
    private Long createTime;
    private BigDecimal balance;

    public VirtualWalletBo(Long id) {
        Date date = new Date();
        Long Time = date.getTime();
        this.id = id;
        createTime = Time;
        balance = BigDecimal.ZERO;
    }

    public BigDecimal balance(){
        return this.balance;
    }

    public void debit(BigDecimal amount) throws NoSufficientBalanceException {
        if(this.balance.compareTo(amount) < 0){
            throw new NoSufficientBalanceException();
        }
        this.balance = this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) throws InvalidAmountException {
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidAmountException();
        }
        this.balance = this.balance.add(amount);
    }

    public VirtualWalletBo(Long id, Long createTime, BigDecimal balance) {
        this.id = id;
        this.createTime = createTime;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
