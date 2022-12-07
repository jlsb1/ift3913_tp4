package ua.karatnyk;
import static org.junit.jupiter.api.Assertions.*;

import ua.karatnyk.impl.CurrencyConversion;
import ua.karatnyk.impl.CurrencyConvertor;
import ua.karatnyk.impl.JsonWorker;
import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
public class TestCurrencyConvertor {
    
    private CurrencyConvertor test;
    private CurrencyConversion conversion;


    public static double round(double d){
        return (Math.round(d*100.0))/100.0;
    }

    @Before
	public void init() {
		test = new CurrencyConvertor();
	}

    @Test
	public void testConversionOfConversion() throws ParseException{
        conversion  = new JsonWorker().parser();
        Double a = test.convert(50.0, "USD", "CAD", conversion);
        Double b = test.convert(a, "CAD", "USD", conversion);
        boolean output = (50.0 == b);
        assertTrue(output);
	}

    @Test
    public void convertUnknownDevise() throws ParseException{
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(50, "CAD", "MXN", conversion));
    }

    @Test
    public void deviseSizeToBig() throws ParseException{
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(50, "CAD", "USDD", conversion));
    }

    @Test
    public void deviseSizeToBig2() throws ParseException{
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(50, "CADD", "USD", conversion));
    }

    @Test
    public void deviseSizeToSmall() throws ParseException{
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(50, "CAD", "SD", conversion));
    }

    @Test
    public void deviseSizeToSmall2() throws ParseException{
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(50, "CAD", "USD", conversion));
    }

    @Test 
    public void montantToSmall() throws ParseException{
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(-12, "CAD", "USD", conversion));
    }

    @Test 
    public void montantToBig() throws ParseException{
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(14377, "CAD", "USD", conversion));
    }

    @Test 
    public void montantLimit() throws ParseException{
        conversion  = new JsonWorker().parser();
        assertNotNull(test.convert(0, "CAD", "USD", conversion));
    }

    @Test 
    public void montantUpperLimit() throws ParseException{
        conversion  = new JsonWorker().parser();
        assertNotNull(test.convert(10000, "CAD", "USD", conversion));
    }

    @Test 
    public void gotToBig() throws ParseException{
        conversion  = new JsonWorker().parser();
        assertNull(test.convert(test.convert(9999, "USD", "CAD", conversion), "CAD", "USD", conversion));
    }

    public static void main(String[] args) {
        System.out.println("allo");
    }
}
