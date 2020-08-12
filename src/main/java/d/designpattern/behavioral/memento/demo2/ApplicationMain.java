package d.designpattern.behavioral.memento.demo2;

import java.util.Scanner;

/**
 * 针对 demo1 的问题，对代码做两点修改：
 *
 * 1，定义一个独立的类（Snapshot 类）来表示快照，而不是复用 InputText 类。这个类只暴露 get() 方法，没有 set() 等任何修改内部状态的方法。
 * 2，在 InputText 类中，我们把 setText() 方法重命名为 restoreSnapshot() 方法，用意更加明确，只用来恢复对象。
 *
 * 典型的备忘录模式的代码实现：
 *
 * 那备忘录模式跟“备份”有什么区别和联系呢？实际上，这两者的应用场景很类似，都应用在防丢失、恢复、撤销等场景中。
 * 它们的区别在于，备忘录模式更侧重于代码的设计和实现，备份更侧重架构设计或产品设计
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
                Snapshot snapshot = snapshotHolder.popSnapshot();
                inputText.restoreSnapshot(snapshot);
            } else {
                snapshotHolder.pushSnapshot(inputText.createSnapshot());
                inputText.append(input);
            }
        }
    }

}
