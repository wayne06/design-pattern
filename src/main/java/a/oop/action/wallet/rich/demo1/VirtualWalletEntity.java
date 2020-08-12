package a.oop.action.wallet.rich.demo1;

import java.math.BigDecimal;

public class VirtualWalletEntity {
    private Long       id;
    private Long       createTime;
    private BigDecimal balance;

    public VirtualWalletEntity(Long id, Long createTime, BigDecimal balance) {
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
