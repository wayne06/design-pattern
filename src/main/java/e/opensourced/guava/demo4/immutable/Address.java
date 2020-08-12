package e.opensourced.guava.demo4.immutable;

public class Address {

    private String province;

    private String city;

    public Address(String province, String city) {
        this.province = province;
        this.city = city;
    }

    // 有 getter 方法，也有 setter 方法

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
