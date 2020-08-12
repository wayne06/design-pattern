package a.oop.action.wallet.anemic;

import java.math.BigDecimal;

public class VirtualWalletService {

    private VirtualWalletRepository walletRepository;
    private VirtualWalletTransactionRepository transactionRepository;

    public VirtualWalletBo getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = walletRepository.getWalletEntity(walletId);
        VirtualWalletBo walletBo = convert(walletEntity);
        return walletBo;
    }

    public BigDecimal getBalance(Long walletId) {
        return walletRepository.getBalance(walletId);
    }

    public void debit(Long walletId, BigDecimal amount) throws InsufficientBalanceException {
        BigDecimal balance = walletRepository.getBalance(walletId);
        if (balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient Balance.");
        }
        walletRepository.updateBalance(walletId, balance.subtract(amount));
    }

    public void credit(Long walletId, BigDecimal amount) throws InvalidAmountException {
        BigDecimal balance = walletRepository.getBalance(walletId);
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException("Invalid Amount.");
        }
        walletRepository.updateBalance(walletId, balance.add(amount));
    }

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) throws InvalidAmountException, Exception {
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
            transactionRepository.updateStatus(transactionId, Status.CLOSED);
            throw new InvalidAmountException("Invalid Amount.");
        }  catch (Exception e) {
            transactionRepository.updateStatus(transactionId, Status.FAILED);
            throw new Exception("Transfer failed.");
        }
        transactionRepository.updateStatus(transactionId, Status.EXECUTED);
    }

    private VirtualWalletBo convert(VirtualWalletEntity walletEntity) {
        return null;
    }

}
