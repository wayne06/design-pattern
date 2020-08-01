package oop.action.wallet.rich.demo1;

import java.math.BigDecimal;

public class VirtualWalletService {

    private VirtualWalletRepository walletRepository;
    private VirtualWalletTransactionRepository transactionRepository;

    public VirtualWallet getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = walletRepository.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        return wallet;
    }

    public BigDecimal getBalance(Long walletId) {
        return walletRepository.getBalance(walletId);
    }

    public void debit(Long walletId, BigDecimal amount) throws InsufficientBalanceException {
        VirtualWalletEntity walletEntity = walletRepository.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.debit(amount);
        walletRepository.updateBalance(walletId, wallet.balance());
    }

    public void credit(Long walletId, BigDecimal amount) throws InvalidAmountException {
        VirtualWalletEntity walletEntity = walletRepository.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.credit(amount);
        walletRepository.updateBalance(walletId, wallet.balance());
    }

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) throws InvalidAmountException {
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setFromWalletId(fromWalletId);
        transactionEntity.setToWalletId(toWalletId);
        transactionEntity.setStatus(Status.TO_BE_EXECUTES);
        Long transactionId = transactionRepository.saveTransaction(transactionEntity);
        try {
            debit(fromWalletId, amount);
            credit(toWalletId, amount);
        } catch (InsufficientBalanceException | InvalidAmountException e) {
            e.printStackTrace();
            throw new InvalidAmountException("Invalid Amount.");
        }
        transactionRepository.updateStatus(transactionId, Status.EXECUTED);
    }

    private VirtualWallet convert(VirtualWalletEntity walletEntity) {
        return null;
    }

}
