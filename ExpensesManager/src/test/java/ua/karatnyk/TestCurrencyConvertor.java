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
        String[] devises = {"USD", "CAD", "GBP", "EUR", "CHF", "INR", "AUD"};
        int devisesLenght = devises.length;
        System.out.println(convertor.convert(33, "USD", "CAD", conversion));
        for (int i = 0; i < devisesLenght; i++) {
            for (int j = 0; j < devisesLenght; j++) {
                if(i != j){
                    System.out.println(devises[i]+" "+devises[j]);
                    //test les motants
                    Double normal = convertor.convert(50, devises[i], devises[j], conversion);
                    Double below = convertor.convert(-1, devises[i], devises[j], conversion);
                    Double over = convertor.convert(12333, devises[i], devises[j], conversion);
                    System.out.println(normal+" "+below+" "+over);
                }
            }
            System.out.println("----------------------");
        }
    }

    public static void main(String[] args) throws ParseException{
        blackBox();
    }
}
