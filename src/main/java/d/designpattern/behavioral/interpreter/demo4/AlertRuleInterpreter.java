package d.designpattern.behavioral.interpreter.demo4;

import java.util.Map;

/**
 * 实际上，可以把自定义的告警规则，看作一种特殊“语言”的语法规则。
 * 我们实现一个解释器，能够根据规则，针对用户输入的数据，判断是否触发告警。
 * 利用解释器模式，把解析表达式的逻辑拆分到各个小类中，避免大而复杂的大类的出现
 */
public class AlertRuleInterpreter {

    private Expression expression;

    public AlertRuleInterpreter(String ruleExpression) {
        this.expression = new OrExpression(ruleExpression);
    }

    public boolean interpret(Map<String, Long> stats) {
        return expression.interpret(stats);
    }
}
