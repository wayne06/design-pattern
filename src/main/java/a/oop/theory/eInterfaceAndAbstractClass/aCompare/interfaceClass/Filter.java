package a.oop.theory.eInterfaceAndAbstractClass.aCompare.interfaceClass;

/**
 * 接口的特点：
 *
 * 1. 接口不能包含属性（也就是成员变量）
 *
 * 2. 接口只能声明方法，方法不能包含代码实现
 *
 * 3. 类实现接口时，必须实现接口中声明的所有方法
 *
 *
 * 相对于抽象类的 is-a 关系来说，接口表示一种 has-a 关系，表示具有某些功能。
 * 对于接口，有一个更加形象的叫法，那就是协议（contract）
 *
 */
public interface Filter {
    void doFilter(RpcRequest req) throws RpcException;
}
