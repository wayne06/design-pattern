package designpattern.structural.composite.demo2;

/**
 * 如果开发的是一个大型系统，从扩展性（文件或目录可能会对应不同的操作）、业务建模（文件和目录从业务上是两个概念）、
 * 代码的可读性（文件和目录区分对待更加符合人们对业务的认知）的角度来说，最好对文件和目录进行区分设计，定义为 File 和 Directory 两个类。
 *
 * 文件和目录类都设计好了，如何用它们来表示一个文件系统中的目录树结构。具体的代码示例如下所示：
 *
 * 组合模式的定义：
 * “将一组对象（文件和目录）组织成树形结构，以表示一种‘部分 - 整体’的层次结构（目录与子目录的嵌套结构）。
 * 组合模式让客户端可以统一单个对象（文件）和组合对象（目录）的处理逻辑（递归遍历）。”
 *
 * 实际上，刚才讲的这种组合模式的设计思路，与其说是一种设计模式，倒不如说是对业务场景的一种数据结构和算法的抽象。
 * 其中，数据可以表示成树这种数据结构，业务需求可以通过在树上的递归遍历算法来实现。
 *
 */
public class Demo {

    public static void main(String[] args) {
        MyDirectory fileSystemTree = new MyDirectory("/");

        MyDirectory node_a = new MyDirectory("/a");
        MyDirectory node_b = new MyDirectory("/b");
        fileSystemTree.addSubNode(node_a);
        fileSystemTree.addSubNode(node_b);

        MyFile node_a_1 = new MyFile("/a/1.txt");
        MyFile node_a_2 = new MyFile("/a/2.txt");
        node_a.addSubNode(node_a_1);
        node_a.addSubNode(node_a_2);

        MyDirectory node_a_movies = new MyDirectory("/a/movies");
        node_a.addSubNode(node_a_movies);
        MyFile node_a_movies_3 = new MyFile("/a/movies/3.avi");
        node_a_movies.addSubNode(node_a_movies_3);

        MyDirectory node_b_docs = new MyDirectory("/b/docs");
        node_b.addSubNode(node_b_docs);
        MyFile node_b_docs_4 = new MyFile("/b/docs/4.txt");
        node_b_docs.addSubNode(node_b_docs_4);


        System.out.println(fileSystemTree.getPath() + " : " + fileSystemTree.countNumOfFiles());
        System.out.println(node_a.getPath() + " : " + node_a.countNumOfFiles());

    }

}
