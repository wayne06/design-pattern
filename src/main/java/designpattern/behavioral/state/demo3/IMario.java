package designpattern.behavioral.state.demo3;

/**
 * 所有状态类的接口
 */
public interface IMario {

    State getName();

    void obtainMushRoom();

    void obtainCape();

    void obtainFireFlower();

    void meetMonster();

}
