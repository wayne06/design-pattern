package e.opensourced.guava.demo4.deeplyimmutable;

/**
 * 深度不变模式：对象包含的引用对象也不可变
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
