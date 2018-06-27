package simplecsvpredictor.dev;
import java.util.ArrayList;
import java.util.List;

public abstract class Predictor implements IModel{
    
    private List<Double> x;
    private List<Double> y;

    
    /**
     * Infer for every ranks.
     * 
     * @return Predicted outputs
     */
    public List<Double> predictAll(){
        List<Double> predictions = new ArrayList<Double>();
        for(int i=0; i<y.size();i++) {
            predictions.add(predict(i));
        }
        return predictions;
    }
    
    /**
     * Infer next value.
     * 
     * @return Predicted value
     */
    public Double predictNext(){
        return predict(y.size());
    }
    
    /**
     * Infer next value.
     * 
     * @return Predicted value
     */
    public List<Double> predictNext(int n){
        List<Double> predictions = new ArrayList<Double>();
        for(int i=0; i<n;i++) {
            predictions.add(predict(getY().size()+i));
        }
        return predictions;
    }
   

    /**
     * Get the x value or i if undefined.
     * @param i
     * @return
     */
    public double getX(int i) {
        double value = i;
        //if x is defined and value exists
        if(x != null && !x.isEmpty() && x.get(i)!=null) {
            value=x.get(i);
        }
        return value;
    }
    
    /**
     * Get the y value at i rank
     * @param i rank
     * @return the value
     */
    public Double getY(int i) {
        return y.get(i);
    }
    
    /**
     * Get missing ranks from a list. 
     * 
     * @param values The list
     * @return missing ranks
     */
    private List<Integer> getMissingValuesRanks(List<Double> values){
        List<Integer> ranks = new ArrayList<>();
        for (int i=0; i<values.size();i++) {
            if(values.get(i)==null) {
                ranks.add(i);
            }
        }
        return ranks;
    }
    
    
    public List<Double> getX() {
        return x;
    }

    public void setX(List<Double> x) {
        this.x = x;
    }

    public List<Double> getY() {
        return y;
    }

    public void setY(List<Double> y) {
        this.y = y;
    }
    
    
}
