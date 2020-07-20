package designpattern.behavioral.interpreter.demo2;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 借助解析器模式拆分：
 *
 * 解释器模式的代码实现比较灵活，没有固定的模板。应用设计模式主要是应对代码的复杂性，实际上，解释器模式也不例外。
 * 它的代码实现的核心思想，就是将语法解析的工作拆分到各个小类中，以此来避免大而全的解析类。
 * 一般的做法是，将语法规则拆分成一些小的独立的单元，然后对每个单元进行解析，最终合并为对整个语法规则的解析。
 *
 * 前面定义的语法规则有两类表达式，一类是数字，一类是运算符，运算符又包括加减乘除。
 * 利用解释器模式，我们把解析的工作拆分到 NumberExpression、AdditionExpression、SubstractionExpression、
 * MultiplicationExpression、DivisionExpression 这样五个解析类中。
 *
 * 按照这个思路对代码进行重构，重构之后的代码如下所示。
 * 当然，因为加减乘除表达式的解析比较简单，利用解释器模式的设计思路，看起来有点过度设计。
 */
public class ExpressionInterpreter {

    private Deque<Expression> numbers = new LinkedList<>();

    public long interpreter(String expression) {
        String[] elements = expression.split(" ");
        int length = elements.length;
        for (int i = 0; i < (length + 1) / 2; i++) {
            numbers.addLast(new NumberExpression(elements[i]));
        }

        for (int i = (length + 1) / 2; i < length; i++) {
            String operator = elements[i];
            boolean isValid = "+".equals(operator) || "-".equals(operator) || "*".equals(operator) || "/".equals(operator);
            if (!isValid) {
                throw new RuntimeException("Expression is invalid: " + expression);
            }

            Expression exp1 = numbers.pollFirst();
            Expression exp2 = numbers.pollFirst();
            Expression combinedExp = null;
            if (operator.equals("+")) {
                combinedExp = new AdditionExpression(exp1, exp2);
            } else if (operator.equals("-")) {
                combinedExp = new SubstractionExpression(exp1, exp2);
            } else if (operator.equals("*")) {
                combinedExp = new MultiplicationExpression(exp1, exp2);
            } else if (operator.equals("/")) {
                combinedExp = new DivisionExpression(exp1, exp2);
            }
            long result = combinedExp.interpret();
            numbers.addFirst(new NumberExpression(result));
        }

        if (numbers.size() != 1) {
            throw new RuntimeException("Expression is invalid: " + expression);
        }

        return numbers.pop().interpret();
    }

}
