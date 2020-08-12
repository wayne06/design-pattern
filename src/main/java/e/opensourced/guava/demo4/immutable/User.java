package e.opensourced.guava.demo4.immutable;

/**
 * 普通不变模式：对象中包含的引用对象是可以改变的
 */
public class User {

    private String name;

    private int age;

    private Address address;

    // 只有 getter 方法，无 setter 方法

    public User(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }
}
