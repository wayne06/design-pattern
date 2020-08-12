package d.designpattern.behavioral.interpreter.demo1;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 假设定义了一个新的加减乘除计算“语言”，语法规则如下：
 * 1. 运算符只包含加、减、乘、除，并且没有优先级的概念；
 * 2. 表达式中，先书写数字，后书写运算符，空格隔开；
 * 3. 按照先后顺序，取出两个数字和一个运算符计算结果，结果重新放入数字的最头部位置，循环上述过程，直到只剩下一个数字，这个数字就是表达式最终的计算结果
 *
 * 用户按照上面的规则书写表达式，传递给 interpret() 函数，就可以得到最终的计算结果
 *
 * 所有的解析逻辑都耦合在一个函数中，这样显然是不合适的。这个时候就要考虑拆分代码，将解析逻辑拆分到独立的小类中。
 *
 * 拆分操作借助解释器模式：详见 demo2
 *
 */
public class ExpressionInterpreter {

    private Deque<Long> numbers = new LinkedList<>();

    public long interpret(String expression) {
        String[] elements = expression.split(" ");
        int length = elements.length;
        for (int i = 0; i < (length + 1) / 2; i++) {
            numbers.addLast(Long.parseLong(elements[i]));
        }

        for (int i = (length + 1) / 2; i < length; i++) {
            String operator = elements[i];
            boolean isValid = "+".equals(operator) || "-".equals(operator) || "*".equals(operator) || "/".equals(operator);
            if (!isValid) {
                throw new RuntimeException("Expression is invalid: " + expression);
            }

            long number1 = numbers.pollFirst();
            long number2 = numbers.pollFirst();
            long result = 0;
            if (operator.equals("+")) {
                result = number1 + number2;
            } else if (operator.equals("-")) {
                result = number1 - number2;
            } else if (operator.equals("*")) {
                result = number1 * number2;
            } else if (operator.equals("/")) {
                result = number1 / number2;
            }
            numbers.addFirst(result);
        }

        if (numbers.size() != 1) {
            throw new RuntimeException("Expression is invalid: " + expression);
        }

        return numbers.pop();
    }

}
