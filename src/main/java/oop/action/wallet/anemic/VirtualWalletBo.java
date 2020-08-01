package oop.action.wallet.anemic;

import java.math.BigDecimal;

public class VirtualWalletBo {
    private Long id;
    private Long createTime;
    private BigDecimal balance;

    public VirtualWalletBo(Long id, Long createTime, BigDecimal balance) {
        this.id = id;
        this.createTime = createTime;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
