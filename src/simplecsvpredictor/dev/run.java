package simplecsvpredictor.dev;
import java.util.List;

/**
 * Use case example for csv next value prediction.
 * @author Simon H.
 *
 */
public class run {

    public static void main(String[] args) {
        
         CSVutils csv = new CSVutils("data.csv"); 
         List<Double> data=  csv.readCsvToList(0);
         SimplePredictor model = new SimplePredictor(data);
         model.fit();
         System.out.println("Next values :" + model.predictNext(10));
         System.out.println("score : "+model.evaluate());
        System.out.println(model.predictAll());
        System.out.println(model.getY());

        CSVutils csv2 = new CSVutils("data2.csv", ";");
        List<Double> dataX = csv2.readCsvToList(0);
        List<Double> dataY = csv2.readCsvToList(1);
        SimplePredictor model2 = new SimplePredictor(dataX, dataY);
        model2.fit();
        System.out.println("Next values :" + model2.predictNext(2));
        System.out.println("score : " + model2.evaluate());

    }

}


