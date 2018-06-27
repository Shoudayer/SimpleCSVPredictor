package simplecsvpredictor.dev;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple CSV reader, that converts to list.
 * @author Simon H.
 *
 */
public class CSVutils {
    
    private String filename;
    private String separator=",";
    
    /**
     * Constructor.
     * @param filename Path to file
     */
    public CSVutils(String filename) {
           this.filename=filename;
    }

    /**
     * Constructor.
     * @param filename Path to file
     * @param sep The csv separator default ','
     */
    public CSVutils(String filename, String sep) {
        this.filename=filename;
        this.separator=sep;
    }
    
    /**
     * Read the specified .csv column.
     * 
     * @param col rank starting from 0.
     * @return column converted to list
     */
    public List<Double> readCsvToList(int col) {
        ArrayList<Double> rows= new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
    
            String sCurrentLine;
            
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
                if(!sCurrentLine.isEmpty()) {
                    if(sCurrentLine.contains(separator) && sCurrentLine.split(separator).length>=col) {
                        rows.add(Double.parseDouble(sCurrentLine.split(separator)[col].trim()));
                    }else if(col==0 && !sCurrentLine.contains(separator)){
                        rows.add(Double.parseDouble(sCurrentLine.trim()));
                    }
                }else {
                    rows.add(null);
                }
            }
    
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return rows;
    }

}
