package com.mypayroll.app;



//import junit.framework.TestCase;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class Test
{

        public static void main(String[] args) {
            Result result = JUnitCore.runClasses(ControllerTest.class);
            for (Failure failure : result.getFailures()) {
                System.out.println("fail ho gaya"+failure.toString());
            }
            System.out.println("passed:"+result.wasSuccessful());
        }

}
