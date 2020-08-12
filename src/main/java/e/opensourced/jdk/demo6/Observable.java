package e.opensourced.jdk.demo6;

import java.util.Vector;

/**
 * 观察者模式在 JDK 中的应用
 *
 * 在讲到观察者模式的时候，重点讲解了 Google Guava 的 EventBus 框架，它提供了观察者模式的骨架代码。使用 EventBus，不需要从零开始开发观察者模式。
 *
 * 实际上，Java JDK 也提供了观察者模式的简单框架实现。
 * 在平时的开发中，如果我们不希望引入 Google Guava 开发库，可以直接使用 Java 语言本身提供的这个框架类。
 * 不过，它比 EventBus 要简单多了，只包含两个类：java.util.Observable 和 java.util.Observer。
 * 前者是被观察者，后者是观察者。它们的代码实现也非常简单，为了方便你查看，我直接 copy-paste 到了这里。
 */
public class Observable {

    private boolean changed = false;
    private Vector  obs;

    public Observable() {
        obs = new Vector<>();
    }

    public synchronized void addObserver(Observer o) {
        if (o == null) {
            throw new NullPointerException();
        }
        if (!obs.contains(o)) {
            obs.addElement(o);
        }
    }

    public synchronized void deleteObserver(Observer o) {
        obs.removeElement(o);
    }

    public void notifyObservers() {
        notifyObservers(null);
    }

    public void notifyObservers(Object arg) {
        Object[] arrLocal;
        synchronized (this) {
            if (!changed) {
                return;
            }
            arrLocal = obs.toArray();
            clearChanged();
        }
        for (int i = arrLocal.length - 1; i >= 0; i--) {
            ((Observer) arrLocal[i]).update(this, arg);
        }
    }

    public synchronized void deleteObservers() {
        obs.removeAllElements();
    }

    protected synchronized void setChanged() {
        changed = true;
    }

    protected synchronized void clearChanged() {
        changed = false;
    }

}
