package ua.karatnyk;

import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import ua.karatnyk.impl.CurrencyConversion;
import ua.karatnyk.impl.CurrencyConvertor;
import ua.karatnyk.impl.ExpensesProgramAPI;
import ua.karatnyk.impl.JsonWorker;
import ua.karatnyk.impl.OfflineJsonWorker;

import java.text.ParseException;

import javax.swing.undo.StateEdit;



import ua.karatnyk.impl.CurrencyConversion;
import ua.karatnyk.impl.ExpensesProgramAPI;
import ua.karatnyk.impl.JsonWorker;



public class TestCurrencyConvertor {
    
    private CurrencyConvertor test;
    private CurrencyConversion conversion;


    public static double round(double d){
        return (Math.round(d*100.0))/100.0;
    }

    
    @Before
	public void init() {
		test = new CurrencyConvertor();
        conversion  = new OfflineJsonWorker().parser();
	}

    @Test
	public void conversionOfConversion() throws ParseException{
        Double a = test.convert(50.0, "USD", "CAD", conversion);
        Double b = test.convert(a, "CAD", "USD", conversion);
        boolean output = (50.0 == b);
        assertTrue(output);
	}
    
    @Test(expected = ParseException.class) 
    public void convertUnknownDevise1() throws ParseException{
        CurrencyConvertor.convert(50, "CAD", "MXN", conversion);
    }

    @Test
    public void convertUnknownDevise11() throws ParseException{
        assertNotNull(CurrencyConvertor.convert(50, "CAD", "MXN", conversion));
    }


    @Test(expected = ParseException.class)
    public void convertUnknownDevise2() throws ParseException{
        CurrencyConvertor.convert(50, "MXN", "CAD", conversion);
    }

    @Test
    public void convertUnknownDevise22() throws ParseException{
        assertNotNull(CurrencyConvertor.convert(50, "MXN", "CAD", conversion));
    }

    @Test(expected = ParseException.class)
    public void deviseSizeToBig() throws ParseException{
        CurrencyConvertor.convert(50, "CAD", "USDD", conversion);
    }

    @Test(expected = ParseException.class)
    public void deviseSizeToBig2() throws ParseException{
        CurrencyConvertor.convert(50, "CADD", "USD", conversion);
    }

    @Test(expected = ParseException.class)
    public void deviseSizeToSmall() throws ParseException{
        CurrencyConvertor.convert(50, "CAD", "SD", conversion);
    }

    @Test(expected = ParseException.class)
    public void deviseSizeToSmall2() throws ParseException{
        CurrencyConvertor.convert(50, "CA", "USD", conversion);
    }

    @Test(expected = ParseException.class) 
    public void montantToSmall() throws ParseException{
        CurrencyConvertor.convert(-12, "CAD", "USD", conversion);
    }
    
    @Test
    public void montantToSmall2() throws ParseException{
        assertNotNull(CurrencyConvertor.convert(-12, "CAD", "USD", conversion));
    }
    
    @Test(expected = ParseException.class) 
    public void montantToBig() throws ParseException{
        CurrencyConvertor.convert(14377, "CAD", "USD", conversion);
    }

    @Test
    public void montantToBig2() throws ParseException{
        assertNotNull(CurrencyConvertor.convert(14377, "CAD", "USD", conversion));
    }

    @Test 
    public void montantLimit() throws ParseException{
        assertNotNull(CurrencyConvertor.convert(0, "CAD", "USD", conversion));
    }

    @Test 
    public void montantUpperLimit() throws ParseException{
        assertNotNull(CurrencyConvertor.convert(10000, "CAD", "USD", conversion));
    }

    @Test(expected = ParseException.class) 
    public void gotToBig() throws ParseException{
        CurrencyConvertor.convert(CurrencyConvertor.convert(9999, "USD", "CAD", conversion), "CAD", "USD", conversion);
    }

    @Test
    public void gotToBig2() throws ParseException{
        assertNotNull(CurrencyConvertor.convert(CurrencyConvertor.convert(9999, "USD", "CAD", conversion), "CAD", "USD", conversion));
    }

    @Test
    public void inRange() throws ParseException{
        assertNotNull(CurrencyConvertor.convert(134, "USD", "CAD", conversion));
    }
}

