package simplecsvpredictor.dev;
import java.util.List;


/**
 * A Simple value predictor for list.
 *
 * @author Simon H.
 *
 */
public class SimplePredictor extends Predictor{
    /**
     * Mean delta between X and Y.
     */
    private double delta=0;

    /**
     * Constructor from a given list.
     * 
     * @param y
     */
    public SimplePredictor(List<Double> y) {
        setY(y);
    }
    /**
     * Constructor from a given input and output list.
     * 
     * @param y
     */
    public SimplePredictor(List<Double>x,List<Double> y) {
        setX(x);
        setY(y);
    }
   

    /**
     * Infer at a precise rank.
     * @param x Rank to guess
     * @return Predicted output
     */
    @Override
    public Double predict(int x) {
        return x+delta;
    }
    
    /**
     * Infer for a given input value.
     * @param x The given value
     * @return Predicted output
     */
    @Override
    public Double predict(Double x) {
        return x+delta;
    }
    
    /**
     * Fit using mean delta.
     */
    public void fit() {
        if(getY()!=null) {
            int ySize=getY().size();
            double deltaSum=0;
            for (int i=0; i<getY().size();i++) {
                if(getY(i)!=null) {
                    if(i==1 && deltaSum==0 && ySize>1) {
                        ySize-=1;
                    }
                    deltaSum+=getY(i)-getX(i);
                }else {
                    if(ySize>1)
                        ySize-=1;
                }
            }
            delta=deltaSum/ySize;
            
            }
        
    }
    
    public Double getDelta() {
        return delta;
    }
    


    
}
