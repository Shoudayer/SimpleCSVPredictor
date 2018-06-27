package simplecsvpredictor.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import simplecsvpredictor.dev.CSVutils;
import simplecsvpredictor.dev.SimplePredictor;

public class UnitTests {

    @Test
    public void testCSVread() {
        CSVutils csv = new CSVutils("data.csv"); 
        assertFalse(csv.readCsvToList(0).isEmpty());
    }
    
    @Test(expected=NullPointerException.class)
    public void testSimplePredictorNull() {
        SimplePredictor model = new SimplePredictor(null);
        model.fit();
        model.predictNext();
    }
    
    @Test()
    public void testSimplePredictorDivideZero() {
        ArrayList<Double> a = new ArrayList<>();
        a.add(null);
        SimplePredictor model = new SimplePredictor(a);
        model.fit();
        assertFalse(model.getDelta().isNaN());
    }
    
    @Test()
    public void testSimplePredictorVal() {
        ArrayList<Double> a = new ArrayList<>();
        a.add(0.0);
        a.add(1.4);
        a.add(2.5);
        SimplePredictor model = new SimplePredictor(a);
        model.fit();
        assertTrue(Math.abs(model.predictNext()-3.4)<0.1);
    }
    
    
    
    
    
    

}
