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

       
    }

}


