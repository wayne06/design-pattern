package designpattern.structural.adapter.v2;

/**
 * @author wayne
 *
 * 适配器模式应用场景一：封装有缺陷的接口设计
 *
 * 隔离设计上的缺陷，对外部系统提供的接口进行二次封装，抽象出更好的接口设计
 */
public class CDAdaptor extends CD implements ITarget {

    @Override
    public void function1() {
        staticFunction1();
    }

    @Override
    public void function2() {
        super.uglyNamingFunction2();
    }

    @Override
    public void function3(ParamWrapperDefinition paramWrapper) {
        super.tooManyParamsFunction3(paramWrapper.getParamA(), paramWrapper.getParamB(), paramWrapper.getParamC(), paramWrapper.getParamD());
    }

    @Override
    public void function4() {
        // ...replacement it...
    }

}
