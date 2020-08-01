package oop.action.wallet.rich.demo1;

import java.math.BigDecimal;

public class VirtualWalletController {

    private VirtualWalletService virtualWalletService;

    /**
     * 查询余额
     * @param walletId
     * @return
     */
    public BigDecimal getBalance(Long walletId) {
        return virtualWalletService.getBalance(walletId);
    }

    /**
     * 出账
     * @param walletId
     * @param amount
     */
    public void debit(Long walletId, BigDecimal amount) {
        try {
            virtualWalletService.debit(walletId, amount);
        } catch (InsufficientBalanceException e) {
            e.printStackTrace();
        }
    }

    /**
     * 入账
     * @param walletId
     * @param amount
     */
    public void credit(Long walletId, BigDecimal amount) {
        try {
            virtualWalletService.credit(walletId, amount);
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转帐
     * @param fromWalletId
     * @param toWalletId
     * @param amount
     */
    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        try {
            virtualWalletService.transfer(fromWalletId, toWalletId, amount);
        } catch (Exception | InvalidAmountException e) {
            e.printStackTrace();
        }
    }

}
