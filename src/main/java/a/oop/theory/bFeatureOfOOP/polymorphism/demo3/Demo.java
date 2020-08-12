package a.oop.theory.bFeatureOfOOP.polymorphism.demo3;

/**
 * 如何用 duck-typing 来实现多态特性
 *
 * 一段 python 代码实例：
 *
     class Logger:
     def record(self):
     print(“I write a log into file.”)

     class DB:
     def record(self):
     print(“I insert data into db. ”)

     def test(recorder):
     recorder.record()

     def demo():
     logger = Logger()
     db = DB()
     test(logger)
     test(db)
 *
 * duck-typing 实现多态的方式非常灵活。Logger 和 DB 两个类没有任何关系，既不是继承关系，也不是接口和实现的关系。
 * 但是只要它们都定义了 record() 方法，就可以被传递到 test() 方法中，在实际运行的时候，执行对应的 record() 方法。
 *
 * 也就是说，只要两个类具有相同的方法，就可以实现多态，并不要求两个类之间有任何关系，这就是所谓的 duck-typing，是一些动态语言所特有的语法机制
 *
 * 多态特性能提高代码的可扩展性和复用性，多态也是很多设计模式、设计原则、编程技巧的代码实现基础，
 * 比如策略模式、基于接口而非实现编程、依赖倒置原则、里式替换原则、利用多态去掉冗长的 if-else 语句等等
 *
 */
public class Demo {

}
