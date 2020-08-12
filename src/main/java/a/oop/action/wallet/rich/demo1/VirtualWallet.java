package a.oop.action.wallet.rich.demo1;

import java.math.BigDecimal;

public class VirtualWallet {
    private Long id;
    private Long createTime = System.currentTimeMillis();
    private BigDecimal balance = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public BigDecimal balance() {
        return this.balance;
    }

    public void debit(BigDecimal amount) throws InsufficientBalanceException {
        if (this.balance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient Balance.");
        }
        this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) throws InvalidAmountException {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAmountException("Invalid Amount.");
        }
        this.balance.add(amount);
    }
}
