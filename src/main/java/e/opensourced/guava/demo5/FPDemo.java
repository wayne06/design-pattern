package e.opensourced.guava.demo5;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 从一组字符串数组中，过滤出长度小于等于 3 的字符串，并且求得这其中的最大长度。
 *
 * Java 为函数式编程引入了三个新的语法概念：Stream 类、Lambda 表达式和函数接口（Functional Inteface）。
 *
 * 1. Stream 类用来支持通过“.”级联多个函数操作的代码编写方式；
 *
 * 在 Java 中，“.”表示调用某个对象的方法。为了支持这种级联调用方式，我们让每个函数都返回一个通用的类型：Stream 类对象。
 * 在 Stream 类上的操作有两种：中间操作和终止操作。中间操作返回的仍然是 Stream 类对象，而终止操作返回的是确定的值结果。
 *
 * 2. 引入 Lambda 表达式的作用是简化代码编写；
 *
         // Stream中map函数的定义：map 函数接收的参数是一个 Funtion 接口，也就是函数接口
         public interface Stream<T> extends BaseStream<T, Stream<T>> {
             <R> Stream<R> map(Function<? super T, ? extends R> mapper);
             //...省略其他函数...
         }

         // Stream中map的使用方法：
         Stream.of("fo", "bar", "hello").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
            return s.length();
            }
        });

         // 用Lambda表达式简化后的写法：
         Stream.of("fo", "bar", "hello").map(s -> s.length());
 *
 * 3. 函数接口的作用是让我们可以把函数包裹成函数接口，来实现把函数当做参数一样来使用
 *
 * Function、Predicate、Comparator 都是函数接口。C 语言支持函数指针，它可以把函数直接当变量来使用。
 * 但是，Java 没有函数指针这样的语法。所以，它通过函数接口，将函数包裹在接口中，当作变量来使用。
 * 实际上函数接口就是接口。不过有自己特别的地方，那就是要求只含一个未实现的方法。因为这样，Lambda 表达式才能明确知道匹配的是哪个接口。
 * 如果有两个未实现的方法，并且接口入参、返回值都一样，那 Java 在翻译 Lambda 表达式的时候，就不知道表达式对应哪个方法了。
 *
 *
 *
 * 从 Google Guava 的 GitHub Wiki 中可以发现，Google 对函数式编程的使用是很谨慎的，认为过度地使用函数式编程，会导致代码可读性变差
 * 所以，在函数式编程方面，Google Guava 并没有提供太多的支持。
 * 之所以对遍历集合操作做了优化，主要是因为函数式编程一个重要的应用场景就是遍历集合。
 * 如果不使用函数式编程，只能 for 循环，一个一个的处理集合中的数据。
 * 使用函数式编程，可以大大简化遍历集合操作的代码编写，一行代码就能搞定，而且在可读性方面也没有太大损失。
 *
 */
public class FPDemo {

    public static void main(String[] args) {
        Optional<Integer> result = Stream.of("f", "ba", "hello")  // of 返回 Stream<String> 对象
                .map(s -> s.length())  // map 返回 Stream<Integer> 对象
                .filter(l -> l <= 3)  // filter 返回 Stream<Integer> 对象
                .max((o1, o2) -> o1 - o2);  // max 终止操作，返回 Optional<Integer>


        // 还原为函数接口的实现方式
        Optional<Integer> result2 = Stream.of("fo", "bar", "hello")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return s.length();
                    }
                })
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer l) {
                        return l <= 3;
                    }
                })
                .max(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                });

        System.out.println(result.get());
    }
}
