package study.stack;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * 基于栈的简单计算器-基于int类型的加减乘除四则运算
 */
public class SimpleCalculator {

    public static void main(String[] args) {
        String expression = "2 + 3 * 4 - 4 / 2";
//        String expression = "3 / 1 * 4 - 6 / 2 + 20";
        System.out.println("计算表达式： " + expression);
        int calc = calc(expression);
        System.out.println(calc);
    }

    public static int calc(String expression) {
        if (StringUtils.isBlank(expression)) {
            throw new RuntimeException("Invalid input");
        }
        //这里简单的用空格拆分,不做词法分析
        String[] split = expression.split(" ", -1);
        ArrayStack<Integer> digits = new ArrayStack<>(20);
        ArrayStack<String> op = new ArrayStack<>(20);
        for (String s : split) {
            insertAndCalc(digits, op, s);
        }
        while (!StringUtils.isBlank(op.peek())) {
            int operand1 = digits.pop();
            int operand2 = digits.pop();
            String operator = op.pop();
            int calc = calc(operand1, operand2, operator);
            digits.push(calc);
        }
        return digits.pop();
    }

    private static void insertAndCalc(ArrayStack<Integer> digits, ArrayStack<String> op, String s) {
        //此处简化,不是操作符就认为是数字
        if (!isOp(s)) {
            digits.push(Integer.parseInt(s));
        } else {
            String preOp = op.peek();
            if (StringUtils.isBlank(preOp) || Op.getOpByCode(s).getPriority() > Op.getOpByCode(preOp).getPriority()) {
                op.push(s);
                return;
            }
            int operand1 = digits.pop();
            int operand2 = digits.pop();
            String operator = op.pop();
            int calc = calc(operand1, operand2, operator);
            digits.push(calc);
            //此处递归处理，不然计算顺序会错乱
            insertAndCalc(digits, op, s);
        }
    }

    private static int calc(int operand1, int operand2, String operator) {
        switch (operator) {
            case "+":
                System.out.println(operand2 + " + " + operand1);
                return operand2 + operand1;
            case "-":
                System.out.println(operand2 + " - " + operand1);
                return operand2 - operand1;
            case "*":
                System.out.println(operand2 + " * " + operand1);
                return operand2 * operand1;
            case "/":
                System.out.println(operand2 + " / " + operand1);
                return operand2 / operand1;
            default:
                return 0;
        }
    }

    /**
     * 判断是否是加减乘除运算符
     *
     * @param str 字符
     * @return 是否匹配
     */
    private static boolean isOp(String str) {
        return Pattern.matches("^[\\+\\-\\*/]$", str);
    }
}
