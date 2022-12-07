package ua.karatnyk;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import ua.karatnyk.impl.CurrencyConversion;
import ua.karatnyk.impl.CurrencyConvertor;
import ua.karatnyk.impl.ExpensesProgramAPI;
import ua.karatnyk.impl.JsonWorker;
import static org.junit.Assert.*;

import java.text.ParseException;

import javax.swing.undo.StateEdit;

import org.junit.Before;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.Before;

import static org.junit.Assert.*;

import org.junit.Before;

import ua.karatnyk.impl.CurrencyConversion;
import ua.karatnyk.impl.ExpensesProgramAPI;
import ua.karatnyk.impl.JsonWorker;

public class TestCurrencyConvertor {
    
    private ExpensesProgramAPI test;
    private CurrencyConversion conversion;


    public static double round(double d){
        return (Math.round(d*100.0))/100.0;
    }

    @Before
	public void init() {
		test = new CurrencyConvertor();
	}

    @Test
	public void conversionOfConversion(){
        conversion  = new JsonWorker().parser();
        Double a = test.convert(50, "USD", "CAD", conversion);
        Double b = test.convert(a, "CAD", "USD", conversion);
        boolean output = (50 == b);
        assertTrue(output);
	}

    @Test
    public void convertUnknownDevise(){
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(50, "CAD", "MXN", conversion));
    }

    @Test
    public void deviseSizeToBig(){
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(50, "CAD", "USDD", conversion));
    }

    @Test
    public void deviseSizeToBig2(){
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(50, "CADD", "USD", conversion));
    }

    @Test
    public void deviseSizeToSmall(){
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(50, "CAD", "SD", conversion));
    }

    @Test
    public void deviseSizeToSmall2(){
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(50, "CAD", "USD", conversion));
    }

    @Test 
    public void montantToSmall(){
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(-12, "CAD", "USD", conversion));
    }

    @Test 
    public void montantToBig(){
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(14377, "CAD", "USD", conversion));
    }

    @Test 
    public void montantLimit(){
        conversion  = new JsonWorker().parser();
        assertNotNull(test.convert(0, "CAD", "USD", conversion));
    }

    @Test 
    public void montantUpperLimit(){
        conversion  = new JsonWorker().parser();
        assertNotNull(test.convert(10000, "CAD", "USD", conversion));
    }

    @Test 
    public void gotToBig(){
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(test.convert(9999, "USD", "CAD", conversion), "CAD", "USD", conversion));
    }

    public static void main(String[] args) throws ParseException{
        
    }
}
