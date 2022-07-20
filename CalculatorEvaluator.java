import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import antlrcalc.*;

public class CalculatorEvaluator {
    public Float evaluate(String expression) throws ArithmeticException, ParseCancellationException {
        CharStream inputStream = CharStreams.fromString(expression);
        CalcLexer lexer = new CalcLexer(inputStream);
        CalcErrorListener errListener=new CalcErrorListener();
        lexer.removeErrorListeners();
        //every error is treated like an exception, we don't want to continue, if there was a problem in lexer
        lexer.addErrorListener(errListener);
        CalcParser parser = new CalcParser(new CommonTokenStream(lexer));
        parser.setBuildParseTree(true);

        parser.removeErrorListeners();
        //every error is treated as an exception in parser too.
        parser.addErrorListener(errListener);
        ParseTree tree = parser.start();

        EvalVisitor visitor = new EvalVisitor();
        Float result = visitor.visit(tree);
        return result;
    }
}
