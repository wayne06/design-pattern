package oop.theory.cOOPvsPOP;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 面向过程编程：
 *
     struct User {
         char name[64];
         int age;
         char gender[16];
     };

     struct User parse_to_user(char* text) {
         // 将text(“小王&28&男”)解析成结构体struct User
     }

     char* format_to_text(struct User user) {
         // 将结构体struct User格式化成文本（"小王\t28\t男"）
     }

     void sort_users_by_age(struct User users[]) {
         // 按照年龄从小到大排序users
     }

     void format_user_file(char* origin_file_path, char* new_file_path) {
         // open files...
         struct User users[1024]; // 假设最大1024个用户
         int count = 0;
         while(1) { // read until the file is empty
             struct User user = parse_to_user(line);
             users[count++] = user;
         }

         sort_users_by_age(users);

         for (int i = 0; i < count; ++i) {
             char* formatted_user_text = format_to_text(users[i]);
             // write to new file...
             }
             // close files...
     }

     int main(char** args, int argv) {
         format_user_file("/home/zheng/user.txt", "/home/zheng/formatted_users.txt");
     }
 *
 * 面向过程和面向对象最基本的区别就是，代码的组织方式不同。
 * 面向过程风格的代码被组织成了一组方法集合及其数据结构（struct User），方法和数据结构的定义是分开的。
 * 面向对象风格的代码被组织成一组类，方法和数据结构被绑定一起，定义在类中。
 *
 */
public class UserFileFormatter {
    public void format(String userFile, String formattedUserFile) {

        List<User> users = new ArrayList<>();
        File file = new File(userFile);
        BufferedReader reader = null;
        BufferedWriter writer = null;

        // open & read user file
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.parseFrom(line);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // sort users by age
        Collections.sort(users, Comparator.comparingInt(User::getAge));

        try {
            writer = new BufferedWriter(new FileWriter(formattedUserFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // write to new file
        for (int i = 0; i < users.size(); i++) {
            String formattedUserText = users.get(i).formatToText();
            try {
                writer.write(formattedUserText);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            writer.flush();
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
