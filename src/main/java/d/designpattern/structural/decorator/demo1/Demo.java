package d.designpattern.structural.decorator.demo1;

import java.io.*;

public class Demo {

    public static void main(String[] args) throws IOException {
        test1();
        test2();
        test3();
    }

    /**
     * 基于继承的设计方案：
     *
     * 如果 InputStream 只有一个子类 FileInputStream 的话，那我们在 FileInputStream 基础之上，再设计一个孙子类 BufferedFileInputStream，也算是可以接受的，毕竟继承结构还算简单。
     * 但实际上，继承 InputStream 的子类有很多。我们需要给每一个 InputStream 的子类，再继续派生支持缓存读取的子类。
     *
     * 除了支持缓存读取之外，如果我们还需要对功能进行其他方面的增强，比如下面的 DataInputStream 类，支持按照基本数据类型（int、boolean、long 等）来读取数据。
     */
    private static void test3() throws IOException {
        FileInputStream in = new FileInputStream("E:\\test.txt");
        DataInputStream din = new DataInputStream(in);
        int data = din.readInt();
    }

    /**
     * test1()中，我们会觉得 Java IO 的用法比较麻烦，需要先创建一个 FileInputStream 对象，然后再传递给 BufferedInputStream 对象来使用。
     * 那么，Java IO 为什么不设计一个继承 FileInputStream 并且支持缓存的 BufferedFileInputStream 类呢？
     * 这样我们就可以像下面的代码中这样，直接创建一个 BufferedFileInputStream 类对象，打开文件读取数据，用起来岂不是更加简单？
     */
    private static void test2() throws IOException {
        //InputStream bin = new BufferedFileInputStream("E:\\test.txt");
        //byte[] data = new byte[128];
        //while (bin.read(data) != -1) {
        //    //...
        //}
    }

    /**
     * 打开文件 test.txt，从中读取数据。其中，InputStream 是一个抽象类，FileInputStream 是专门用来读取文件流的子类。
     * BufferedInputStream 是一个支持带缓存功能的数据读取类，可以提高数据读取的效率
     */
    private static void test1() throws IOException {
        InputStream in = new FileInputStream("E:\\test.txt");
        InputStream bin = new BufferedInputStream(in);
        byte[] data = new byte[128];
        while (bin.read(data) != -1) {
            //...
        }
    }


}
