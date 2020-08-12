package d.designpattern.behavioral.state.demo1;

/**
 * 有限状态机，英文翻译是 Finite State Machine，缩写为 FSM，简称为状态机。
 * 状态机有 3 个组成部分：状态（State）、事件（Event）、动作（Action）。
 * 其中，事件也称为转移条件（Transition Condition）。事件触发状态的转移及动作的执行。不过，动作不是必须的，也可能只转移状态，不执行任何动作
 *
 * 马里奥形态的转变就是一个状态机。
 * 其中，马里奥的不同形态就是状态机中的“状态”，游戏情节（比如吃了蘑菇）就是状态机中的“事件”，加减积分就是状态机中的“动作”。
 * 比如，吃蘑菇这个事件，会触发状态的转移：从小马里奥转移到超级马里奥，以及触发动作的执行（增加 100 积分）
 *
 * obtainMushRoom()、obtainCape()、obtainFireFlower()、meetMonster() 这几个函数，能够根据当前的状态和事件，更新状态和增减积分
 *
 * 状态机实现方式一：分支逻辑法
 * 参照状态转移图，将每一个状态转移，原模原样地直译成代码。
 * 这样编写的代码会包含大量的 if-else 或 switch-case 分支判断逻辑，甚至是嵌套的分支判断逻辑，所以把这种方法暂且命名为分支逻辑法
 *
 * 对于简单的状态机来说，分支逻辑这种实现方式是可以接受的。但是，对于复杂的状态机来说，这种实现方式极易漏写或者错写某个状态转移。
 * 除此之外，代码中充斥着大量的 if-else 或者 switch-case 分支判断逻辑，可读性和可维护性都很差。
 * 如果哪天修改了状态机中的某个状态转移，我们要在冗长的分支逻辑中找到对应的代码进行修改，很容易改错，引入 bug
 */
public class MarioStateMachine {

    private int score;
    private State currentState;

    public MarioStateMachine() {
        this.score = 0;
        this.currentState = State.SMALL;
    }

    public void obtainMushRoom() {
        if (currentState.equals(State.SMALL)) {
            this.score += 100;
            this.currentState = State.SUPER;
        }
    }

    public void obtainCape() {
        if (currentState.equals(State.SMALL) || currentState.equals(State.SUPER)) {
            this.score += 200;
            this.currentState = State.CAPE;
        }
    }

    public void obtainFireFlower() {
        if (currentState.equals(State.SMALL) || currentState.equals(State.SUPER)) {
            this.score += 300;
            this.currentState = State.FIRE;
        }
    }

    public void meetMonster() {
        if (currentState.equals(State.SUPER)) {
            this.score -= 100;
        }
        if (currentState.equals(State.CAPE)) {
            this.score -= 200;
        }
        if (currentState.equals(State.FIRE)) {
            this.score -= 300;
        }
        currentState = State.SMALL;
        return;
    }

    public int getScore() {
        return this.score;
    }

    public State getCurrentState() {
        return this.currentState;
    }
}
