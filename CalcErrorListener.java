import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class CalcErrorListener extends BaseErrorListener {
    public static final CalcErrorListener INSTANCE = new CalcErrorListener();

    @Override
    public void syntaxError(Recognizer<?,?> recognizer, Object offendingSymbol,
            int line, int charPositionInLine, String msg, RecognitionException e)
            throws ParseCancellationException {
        throw new ParseCancellationException(msg);
    }
}
