package e.opensourced.guava.demo4.deeplyimmutable;

public class Address {

    private String province;

    private String city;

    public Address(String province, String city) {
        this.province = province;
        this.city = city;
    }

    // 只有 getter 方法，没有 setter 方法


    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }
}
