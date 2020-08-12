package f.inaction.darklaunch.v2;

/**
 * 用来抽象从配置文件中得到的灰度规则，以及编程实现的灰度规则
 *
 * 基于这个抽象接口，业务系统可以自己编程实现复杂的灰度规则，然后添加到 DarkRule 中
 */
public interface IDarkFeature {

    boolean enabled();
    boolean dark(long darkTarget);
    boolean dark(String darkTarget);

}
