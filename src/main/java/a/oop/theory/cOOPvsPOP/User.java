package a.oop.theory.cOOPvsPOP;

public class User {
    private String name;
    private int age;
    private String gender;

    public User(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public static User parseFrom(String userInfoText) {
        // 将 text("小王&29&男")解析成类 User
        String[] userArr = userInfoText.split("&");
        User user = new User(userArr[0], Integer.valueOf(userArr[1]), userArr[2]);
        return user;
    }

    public String formatToText() {
        // 将类 User 格式化成文本("小王\t28\t男")
        String text = this.name + "\t" + this.age + "\t" + this.gender;
        return text;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}
