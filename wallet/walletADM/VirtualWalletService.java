package walletADM;


import java.math.BigDecimal;
import java.util.ArrayList;

public class VirtualWalletService {

    public void createVirtualWallet(Long id, Long createTime, BigDecimal balance, ArrayList<VirtualWalletBo> wallets)  {
        try{
            for (VirtualWalletBo wallet : wallets) {
                if (wallet.getId().equals(id)) {
                    throw new HaveSameIdException();
                }
            }
            VirtualWalletBo newWallet = new VirtualWalletBo(id, createTime, balance);
            wallets.add(newWallet);
            System.out.println("Create successfully! Your wallet id: " + id);
        }catch (HaveSameIdException e){
        }

    }

    public VirtualWalletBo getVirtualWallet(Long walletId, ArrayList<VirtualWalletBo> wallets) {
        VirtualWalletBo retWallet = null;
        try{
            for (VirtualWalletBo wallet : wallets) {
                if (wallet.getId().equals(walletId)) {
                    retWallet = wallet;
                }
            }
            if(retWallet == null) throw new NoneOfWalletException();
        }catch (NoneOfWalletException e){
        }
        return retWallet;
    }

    public BigDecimal getBalance(Long walletId, ArrayList<VirtualWalletBo> wallets) {
        BigDecimal retBalance = null;
        VirtualWalletBo virtualWalletBo = getVirtualWallet(walletId, wallets);
        if(virtualWalletBo != null) retBalance = virtualWalletBo.getBalance();
        return retBalance;
    }

    public boolean debit(Long walletId, BigDecimal amount, ArrayList<VirtualWalletBo> wallets){
        boolean flg = true;
        try {
            VirtualWalletBo virtualWalletBo = getVirtualWallet(walletId, wallets);
            if(virtualWalletBo == null) return false;
            BigDecimal balance = getBalance(walletId, wallets);
            if(balance.compareTo(amount) < 0){
                flg = false;
                throw new NoSufficientBalanceException();
            }
            System.out.println("Successfully!");
            virtualWalletBo.setBalance(balance.subtract(amount));
        } catch (NoSufficientBalanceException e) {
        }
        return flg;
    }

    public void credit(Long walletId, BigDecimal amount, ArrayList<VirtualWalletBo> wallets){
        VirtualWalletBo virtualWalletBo = getVirtualWallet(walletId, wallets);
        if(virtualWalletBo == null) return;
        System.out.println("Successfully!");
        BigDecimal balance = getBalance(walletId, wallets);
        virtualWalletBo.setBalance(balance.add(amount));
    }

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount, ArrayList<VirtualWalletBo> wallets){
        VirtualWalletBo fromWallet = getVirtualWallet(fromWalletId, wallets);
        if(fromWallet == null){
            return ;
        }
        VirtualWalletBo toWallet = getVirtualWallet(toWalletId, wallets);
        if(toWallet == null){
            return ;
        }
        boolean flg = debit(fromWalletId, amount, wallets);
        if(!flg) return ; // fail debit
        credit(toWalletId, amount, wallets);
        System.out.println("Successfully!");
    }

}
