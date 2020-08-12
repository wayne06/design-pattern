package d.designpattern.structural.adapter.v7;

/**
 * slf4j统一的接口定义
 *
 * 参考 org.slf4j.Logger 接口，和 ch.qos.logback.classic.Logger 适配器类
 *
 * ch.qos.logback.classic.Logger 实现了 org.slf4j.Logger 接口
 *
 * 所以：在开发业务系统或者开发框架、组件的时候，统一使用 Slf4j 提供的接口来编写打印日志的代码，
 * 具体使用哪种日志框架实现（log4j、logback……），是可以动态地指定的（使用 Java 的 SPI 技术 - Service Provider Interface），只需要将相应的 SDK 导入到项目中即可。
 *
 */
public interface Logger {

    boolean isTraceEnabled();
    void trace(String msg);
    void trace(String format, Object arg);
    void trace(String format, Object arg1, Object arg2);
    void trace(String format, Object[] argArray);
    void trace(String msg, Throwable t);

    boolean isDebugEnabled();
    void debug(String msg);
    void debug(String format, Object arg);
    void debug(String format, Object arg1, Object arg2);
    void debug(String format, Object[] argArray);
    void debug(String msg, Throwable t);

    // ...省略info、warn、error等一堆接口

}
