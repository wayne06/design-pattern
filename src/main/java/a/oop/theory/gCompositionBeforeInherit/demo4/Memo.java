package a.oop.theory.gCompositionBeforeInherit.demo4;

/**
 * 继承改写成组合意味着要做更细粒度的类的拆分。这也就意味着，我们要定义更多的类和接口。
 * 类和接口的增多也就或多或少地增加代码的复杂程度和维护成本。
 * 所以，在实际的项目开发中，我们还是要根据具体的情况，来具体选择该用继承还是组合
 *
 * 如果类之间的继承结构稳定（不会轻易改变），继承层次比较浅（比如，最多有两层继承关系），继承关系不复杂，我们就可以大胆地使用继承。
 * 反之，系统越不稳定，继承层次很深，继承关系复杂，我们就尽量使用组合来替代继承。
 *
 * 除此之外，还有一些设计模式会固定使用继承或者组合。
 * 比如，装饰者模式（decorator pattern）、策略模式（strategy pattern）、组合模式（composite pattern）等都使用了组合关系，
 * 而模板模式（template pattern）使用了继承关系。
 *
 * 从业务含义上，A 类和 B 类并不一定具有继承关系。
 * 比如，Crawler 类和 PageAnalyzer 类，它们都用到了 URL 拼接和分割的功能，但并不具有继承关系（既不是父子关系，也不是兄弟关系）。
 * 仅仅为了代码复用，生硬地抽象出一个父类出来，会影响到代码的可读性。
 * 如果不熟悉背后设计思路的同事，发现 Crawler 类和 PageAnalyzer 类继承同一个父类，而父类中定义的却只是 URL 相关的操作，
 * 会觉得这个代码写得莫名其妙，理解不了。
 *
 * 这个时候，使用组合就更加合理、更加灵活。
 *
     public class Url {
        //...省略属性和方法
     }

     public class Crawler {
         private Url url; // 组合
         public Crawler() {
            this.url = new Url();
         }
         //...
     }

     public class PageAnalyzer {
         private Url url; // 组合
         public PageAnalyzer() {
            this.url = new Url();
         }
         //..
     }
 *
 * 还有一些特殊的场景要求我们必须使用继承。
 * 如果你不能改变一个函数的入参类型，而入参又非接口，为了支持多态，只能采用继承来实现。
 * 比如下面这样一段代码：其中 FeignClient 是一个外部类，我们没有权限去修改这部分代码，
 * 但是我们希望能重写这个类在运行时执行的 encode() 函数。这个时候，我们只能采用继承来实现了。
 *
    public class FeignClient { // feign client框架代码
        //...省略其他代码...
        public void encode(String url) { //... }
    }

    public class CustomizedFeignClient extends FeignClient {
        @Override
        public void encode(String url) { //...重写encode的实现...}
    }

    public void demofunction(FeignClient feignClient) {
        //...
        feignClient.encode(url);
        //...
    }

    // 调用
    FeignClient client = new CustomizedFeignClient();
    demofunction(client);
 *
 *
 *
 */
public class Memo {
}
