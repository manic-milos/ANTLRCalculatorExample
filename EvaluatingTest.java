import junit.framework.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import org.junit.Test;
import org.antlr.v4.runtime.misc.ParseCancellationException;
public class EvaluatingTest extends TestCase{
    public EvaluatingTest() {
        super();
    }
    CalculatorEvaluator e;
    protected void setUp(){
        e=new CalculatorEvaluator();
    }

    protected float delta=0.0001f;
    
    @Test
    public void testLexerFail(){
        assertThrows(ParseCancellationException.class,()->e.evaluate("asdas"));
    }
    
    @Test
    public void testLexerFailLater(){
        assertThrows(ParseCancellationException.class,()->e.evaluate("+asdas"));
    }
    
    @Test
    public void testFailLetterParens(){
        assertThrows(ParseCancellationException.class,()->e.evaluate("+(asdas)"));
    }
    @Test
    public void testFailOnLetter(){
        assertThrows(ParseCancellationException.class,()->e.evaluate("7+z"));
    }
    
    @Test
    public void testEmptyParens(){
        assertThrows(ParseCancellationException.class,()->e.evaluate("+()"));
    }
    @Test
    public void testFloatingPointFailsBeforeParens(){
        assertThrows(ParseCancellationException.class,()->e.evaluate(".(1+0.5)"));
    }
    @Test
    public void testZero(){
        Float result=e.evaluate("0");
        assertEquals(result,0,delta);
    }

    @Test
    public void testNegative(){
        Float result=e.evaluate("-1");
        assertEquals(result,-1,delta);
    }
    @Test
    public void testOrderOfOpsPlusTimes(){
        Float result=e.evaluate("1*2+3");
        assertEquals(result,1*2+3,delta);
        result=e.evaluate("1+2*3");
        assertEquals(result,1+2*3,delta);
        result=e.evaluate("1*2+3*4");
        assertEquals(result,1*2+3*4,delta);
        result=e.evaluate("1+2*3+4");
        assertEquals(result,1+2*3+4,delta);
        result=e.evaluate("(1+2)*(3+4)");
        assertEquals(result,(1+2)*(3+4),delta);
    }

    @Test
    public void testOrderOfOpsMinusDiv(){
        Float result=e.evaluate("1/2-3");
        assertEquals("1/2-3",result,1.0/2-3,delta);
        result=e.evaluate("1-2/3");
        assertEquals("1-2/3",result,1.0-2.0/3,delta);
        result=e.evaluate("1/2-3/4");
        assertEquals("1/2-3/4",result,1.0/2-3.0/4,delta);
        result=e.evaluate("1-2/3-4");
        assertEquals("1-2/3-4",result,1.0-2.0/3-4,delta);
        result=e.evaluate("(1-2)/(3-4)");
        assertEquals("(1-2)/(3-4)",result,(1-2.0)/(3-4),delta);
    }
    @Test
    public void testFloatingPoint(){
        Float result=e.evaluate(".1+0.5");
        assertEquals(".1+0.5",result,.1+0.5,delta);
    }
    
    @Test
    public void testDivisionByZero(){
        Float result=e.evaluate("1/0");
        assertEquals("1/0",result,1.0/0,delta);
    }
    @Test
    public void testNaN(){
        Float result=e.evaluate("0/0");
        assertEquals("0/0",result,0.0/0,delta);
    }
}