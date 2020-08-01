package oop.action.wallet.rich.demo2;

import java.math.BigDecimal;

/**
 * 领域模型 VirtualWallet 类添加了简单的冻结和透支逻辑之后，功能看起来就丰富了很多，代码也没那么单薄了。
 * 如果功能继续演进，我们可以增加更加细化的冻结策略、透支策略、支持钱包账号（VirtualWallet id 字段）自动生成的逻辑
 * （不是通过构造函数经外部传入 ID，而是通过分布式 ID 生成算法来自动生成 ID）等等。
 * VirtualWallet 类的业务逻辑会变得越来越复杂，也就很值得设计成充血模型了。
 */
public class VirtualWallet {

    private Long       id;
    private Long       createTime         = System.currentTimeMillis();
    private BigDecimal balance            = BigDecimal.ZERO;
    private boolean    isAllowedOverdraft = true;
    private BigDecimal overdraftAmount    = BigDecimal.ZERO;
    private BigDecimal frozenAmount       = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId) {
        this.id = preAllocatedId;
    }

    public void freeze(BigDecimal amount) {
        this.frozenAmount = this.frozenAmount.add(amount);
    }

    public void unfreeze(BigDecimal amount) {
        this.frozenAmount = this.frozenAmount.subtract(amount);
    }

    public void increaseOverdraftAmount(BigDecimal amount) {
        this.overdraftAmount = this.overdraftAmount.add(amount);
    }

    public void decreaseOverdraftAmount(BigDecimal amount) {
        this.overdraftAmount = this.overdraftAmount.subtract(amount);
    }

    public void closeOverdraft() {
        this.isAllowedOverdraft = false;
    }

    public void openOverdraft() {
        this.isAllowedOverdraft = true;
    }

    public BigDecimal balance() {
        return this.balance;
    }

    public BigDecimal getAvailableBalance() {
        BigDecimal totalAvailableBalance = this.balance.subtract(this.frozenAmount);
        if (isAllowedOverdraft) {
            totalAvailableBalance = totalAvailableBalance.add(this.overdraftAmount);
        }
        return totalAvailableBalance;
    }

    public void debit(BigDecimal amount) throws InsufficientBalanceException {
        BigDecimal totalAvailableBalance = getAvailableBalance();
        if (totalAvailableBalance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient Balance.");
        }
        this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        this.balance.add(amount);
    }
}
