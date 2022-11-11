package walletRDM;


import java.math.BigDecimal;
import java.util.ArrayList;

public class VirtualWalletService {

    public void createVirtualWallet(Long id, ArrayList<VirtualWalletBo> wallets)  {
        try{
            for (VirtualWalletBo wallet : wallets) {
                if (wallet.getId().equals(id)) {
                    throw new HaveSameIdException();
                }
            }
            VirtualWalletBo newWallet = new VirtualWalletBo(id);
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
        if(virtualWalletBo != null) retBalance = virtualWalletBo.balance();
        return retBalance;
    }

    public void debit(Long walletId, BigDecimal amount, ArrayList<VirtualWalletBo> wallets){
        try {
            VirtualWalletBo virtualWalletBo = getVirtualWallet(walletId, wallets);
            if(virtualWalletBo == null) return;
            virtualWalletBo.debit(amount);
            System.out.println("Successfully!");
        } catch (NoSufficientBalanceException e) {
        }
    }

    public void credit(Long walletId, BigDecimal amount, ArrayList<VirtualWalletBo> wallets){
        try{
            VirtualWalletBo virtualWalletBo = getVirtualWallet(walletId, wallets);
            if(virtualWalletBo == null) return;
            virtualWalletBo.credit(amount);
            System.out.println("Successfully!");
        }catch (InvalidAmountException e) {
        }

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
        try{
            fromWallet.debit(amount);
            toWallet.credit(amount);
            System.out.println("Successfully!");
        } catch (NoSufficientBalanceException | InvalidAmountException e) {

        }
    }

}
