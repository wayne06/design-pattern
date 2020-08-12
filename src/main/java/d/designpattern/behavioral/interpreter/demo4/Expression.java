package d.designpattern.behavioral.interpreter.demo4;

import java.util.Map;

public interface Expression {

    boolean interpret(Map<String, Long> stats);

}
