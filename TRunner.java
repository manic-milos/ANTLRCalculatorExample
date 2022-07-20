
import junit.framework.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TRunner{
    public static void main(String[] args) {
        var result= JUnitCore.runClasses(EvaluatingTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
         }
         System.out.println(result.wasSuccessful());
    }
}


