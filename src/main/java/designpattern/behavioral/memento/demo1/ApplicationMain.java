package designpattern.behavioral.memento.demo1;

import java.util.Scanner;

/**
 * 备忘录模式：
 * 在不违背封装原则的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，以便之后恢复对象为先前的状态
 * 一部分是，存储副本以便后期恢复；另一部分是，要在不违背封装原则的前提下，进行对象的备份和恢复
 *
 * 1.为什么存储和恢复副本会违背封装原则？ 2.备忘录模式是如何做到不违背封装原则的？
 *
 * 编写一个小程序，可以接收命令行的输入。
 * 用户输入文本时，程序将其追加存储在内存文本中；
 * 用户输入“:list”，程序在命令行中输出内存文本的内容；
 * 用户输入“:undo”，程序会撤销上一次输入的文本，也就是从内存文本中将上次输入的文本删除掉
 *
 * 要在不违背封装原则的前提下，进行对象的备份和恢复。而下面的代码并不满足这一点，主要体现在下面两方面：
 * 1、为了能用快照恢复 InputText 对象，在 InputText 类中定义了 setText() 函数，但这个函数有可能会被其他业务使用，所以，暴露不应该暴露的函数违背了封装原则；
 * 2、快照本身是不可变的，理论上讲，不应该包含任何 set() 等修改内部状态的函数，但在下面的代码实现中，“快照“这个业务模型复用了
 * InputText 类的定义，而 InputText 类本身有一系列修改内部状态的函数，所以，用 InputText 类来表示快照违背了封装原则。
 *
 * 针对以上问题，对代码做两点修改，见 demo2
 * 其一，定义一个独立的类（Snapshot 类）来表示快照，而不是复用 InputText 类。这个类只暴露 get() 方法，没有 set() 等任何修改内部状态的方法。
 * 其二，在 InputText 类中，我们把 setText() 方法重命名为 restoreSnapshot() 方法，用意更加明确，只用来恢复对象。
 *
 *
 *
 *
 *
 */
public class ApplicationMain {

    public static void main(String[] args) {

        InputText inputText = new InputText();

        SnapshotHolder snapshotHolder = new SnapshotHolder();

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {

            String input = scanner.next();

            if (input.equals(":list")) {
                System.out.println(inputText.toString());
            } else if (input.equals(":undo")) {
                InputText snapshot = snapshotHolder.popSnapshot();
                inputText.setText(snapshot.getText());
            } else {
                snapshotHolder.pushSnapshot(inputText);
                inputText.append(input);
            }
        }
    }

}
