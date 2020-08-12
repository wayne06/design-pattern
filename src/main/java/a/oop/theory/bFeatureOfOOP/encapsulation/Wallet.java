package a.oop.theory.bFeatureOfOOP.encapsulation;

import d.designpattern.creational.singleton.s10singletonincluster.IdGenerator;

import java.math.BigDecimal;

/**
 * 封装也叫作信息隐藏或者数据访问保护。类通过暴露有限的访问接口，授权外部仅能通过类提供的方式（或者叫函数）来访问内部信息或者数据
 *
 * 参照封装特性，对钱包的四个属性的访问方式进行了限制。调用者只允许通过下面这六个方法来访问或者修改钱包里的数据
 *
 * 之所以这样设计，是因为从业务的角度来说，id、createTime 在创建钱包的时候就确定好了，之后不应该再被改动，
 * 所以，我们并没有在 Wallet 类中，暴露 id、createTime 这两个属性的任何修改方法，比如 set 方法。
 * 而且，这两个属性的初始化设置，对于 Wallet 类的调用者来说，也应该是透明的，
 * 所以，我们在 Wallet 类的构造函数内部将其初始化设置好，而不是通过构造函数的参数来外部赋值
 *
 * 对于钱包余额 balance 这个属性，从业务的角度来说，只能增或者减，不会被重新设置。
 * 所以，我们在 Wallet 类中，只暴露了 increaseBalance() 和 decreaseBalance() 方法，并没有暴露 set 方法。
 * 对于 balanceLastModifiedTime 这个属性，它完全是跟 balance 这个属性的修改操作绑定在一起的。
 * 只有在 balance 修改的时候，这个属性才会被修改。
 * 所以，我们把 balanceLastModifiedTime 这个属性的修改操作完全封装在了 increaseBalance() 和 decreaseBalance() 两个方法中，
 * 不对外暴露任何修改这个属性的方法和业务细节。这样也可以保证 balance 和 balanceLastModifiedTime 两个数据的一致性
 *
 * 对于封装这个特性，我们需要编程语言本身提供一定的语法机制来支持。这个语法机制就是访问权限控制。
 *
 * 如果我们对类中属性的访问不做限制，那任何代码都可以访问、修改类中的属性，虽然这样看起来更加灵活，
 * 但从另一方面来说，过度灵活也意味着不可控，属性可以随意被以各种奇葩的方式修改，
 * 而且修改逻辑可能散落在代码中的各个角落，势必影响代码的可读性、可维护性
 *
 * 除此之外，类仅仅通过有限的方法暴露必要的操作，也能提高类的易用性。
 * 如果我们把类属性都暴露给类的调用者，调用者想要正确地操作这些属性，就势必要对业务细节有足够的了解。
 * 而这对于调用者来说也是一种负担。
 * 相反，如果我们将属性封装起来，暴露少许的几个必要的方法给调用者使用，调用者就不需要了解太多背后的业务细节，用错的概率就减少很多
 */
public class Wallet {

    private String id;
    private long createTime;
    private BigDecimal balance;
    private long balanceLastModifiedTimes;
    // ...省略其他属性...

    public Wallet() {
        this.id = String.valueOf(IdGenerator.getInstance().getId());
        this.createTime = System.currentTimeMillis();
        this.balance = BigDecimal.ZERO;
        this.balanceLastModifiedTimes = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public long getBalanceLastModifiedTimes() {
        return balanceLastModifiedTimes;
    }

    public void decreaseBalance(BigDecimal decreasedAmount) throws Exception {
        if (decreasedAmount.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Invalid amount.");
            throw new Exception();
        }
        if (decreasedAmount.compareTo(this.balance) > 0) {
            System.out.println("Insufficient amout.");
            throw new Exception();
        }
        this.balance.subtract(decreasedAmount);
        this.balanceLastModifiedTimes = System.currentTimeMillis();
    }

    public void increaseBalance(BigDecimal increasedAmount) throws Exception {
        if (increasedAmount.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Invalid amount.");
            throw new Exception();
        }
        this.balance.add(increasedAmount);
        this.balanceLastModifiedTimes = System.currentTimeMillis();
    }
}
