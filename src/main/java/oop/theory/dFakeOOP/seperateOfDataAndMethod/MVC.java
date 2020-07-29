package oop.theory.dFakeOOP.seperateOfDataAndMethod;

/**
 * 面向对象编程过程中，常见的面向过程风格的代码就是：数据定义在一个类中，方法定义在另一个类中。
 *
 * 实际上，如果是基于 MVC 三层结构做 Web 方面的后端开发，这样的代码你可能天天都在写。
 *
 * 传统的 MVC 结构分为 Model 层、Controller 层、View 层这三层。
 * 不过，在做前后端分离之后，三层结构在后端开发中，会稍微有些调整，被分为 Controller 层、Service 层、Repository 层。
 * Controller 层负责暴露接口给前端调用，Service 层负责核心业务逻辑，Repository 层负责数据读写。
 * 而在每一层中，我们又会定义相应的 VO（View Object）、BO（Business Object）、Entity。
 * 一般情况下，VO、BO、Entity 中只会定义数据，不会定义方法，所有操作这些数据的业务逻辑都定义在对应的 Controller 类、Service 类、Repository 类中。
 * 这就是典型的面向过程的编程风格。
 *
 * 实际上，这种开发模式叫作基于贫血模型的开发模式，也是我们现在非常常用的一种 Web 项目的开发模式
 */
public class MVC {
}
