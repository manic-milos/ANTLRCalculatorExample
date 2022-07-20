import java.util.Scanner;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class App {
    public static void main(String[] args) {
        String input;
        Scanner sc = new Scanner(System.in);
        CalculatorEvaluator eval = new CalculatorEvaluator();
        while (true) {
            try {
                System.out.print(">");
                input = sc.nextLine();
                System.out.println(eval.evaluate(input));
            } catch (ArithmeticException e) {
                System.out.println("Arithmetic exception:" + e.getMessage());
            } catch (ParseCancellationException e) {
                System.out.println("Syntax error:" + e.getMessage());
            }
        }

    }
}

