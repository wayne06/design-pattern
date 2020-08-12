package d.designpattern.structural.adapter.v2;

/**
 * @author wayne
 *
 * 使用适配器模式进行重构
 */
public interface ITarget {

    void function1();

    void function2();

    void function3(ParamWrapperDefinition paramWrapper);

    void function4();

}
