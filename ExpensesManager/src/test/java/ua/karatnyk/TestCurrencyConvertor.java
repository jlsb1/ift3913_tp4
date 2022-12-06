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

import org.junit.Before;


public class TestCurrencyConvertor {
    private static CurrencyConversion conversion;
    private static CurrencyConvertor convertor;


    public static void blackBox() throws ParseException{
        conversion = new JsonWorker().parser();
        convertor = new CurrencyConvertor();

        System.out.println(convertor.convert(33, "USD", "CAD", conversion));
    }

    public static void main(String[] args) throws ParseException{
        blackBox();
        System.out.println("allo");
    }
}
