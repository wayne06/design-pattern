package d.designpattern.behavioral.state.demo2;

import static d.designpattern.behavioral.state.demo2.State.*;

/**
 * 状态机实现方式二：查表法
 *
 * 除了用状态转移图来表示之外，状态机还可以用二维表来表示，
 * 在二维表中，第一维表示当前状态，第二维表示事件，值表示当前状态经过事件之后，转移到的新状态及其执行的动作
 *
 * 相对于分支逻辑的实现方式，查表法的代码实现更加清晰，可读性和可维护性更好。
 * 当修改状态机时，我们只需要修改 transitionTable 和 actionTable 两个二维数组即可。
 * 实际上，如果我们把这两个二维数组存储在配置文件中，当需要修改状态机时，甚至可以不修改任何代码，只需要修改配置文件就可以了
 */
public class MarioStateMachine {

    private int   score;
    private State currentState;

    private static final State[][] transitionTable = {
            {SUPER, CAPE, FIRE, SMALL},  // state==0时（即 Small 状态时），发生 E1、E2、E3、E4后会变化为什么状态
            {SUPER, CAPE, FIRE, SMALL},  // state==1时（即 Small 状态时），发生 E1、E2、E3、E4后会变化为什么状态
            {CAPE, CAPE, CAPE, SMALL},  // state==2时（即 Small 状态时），发生 E1、E2、E3、E4后会变化为什么状态
            {FIRE, FIRE, FIRE, SMALL}  // state==3时（即 Small 状态时），发生 E1、E2、E3、E4后会变化为什么状态
    };

    private static final int[][] actionTable = {
            {+100, +200, +300, +0},  // state==0时（即 Small 状态时），发生 E1、E2、E3、E4后会变化多少积分
            {+0, +200, +300, -100},  //..
            {+0, +0, +0, -200},  //..
            {+0, +0, +0, -300}  //..
    };

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = SMALL;
    }

    public void obtainMushRoom() {
        executeEvent(Event.GOT_MUSHROOM);
    }

    public void obtainCape() {
        executeEvent(Event.GOT_CAPE);
    }

    public void obtainFire() {
        executeEvent(Event.GOT_FIRE);
    }

    public void meetMonster() {
        executeEvent(Event.MET_MONSTER);
    }

    private void executeEvent(Event event) {
        int stateValue = currentState.getValue();
        int eventValue = event.getValue();
        this.currentState = transitionTable[stateValue][eventValue];
        this.score = actionTable[stateValue][eventValue];
    }

    public int getScore() {
        return score;
    }

    public State getCurrentState() {
        return currentState;
    }
}
