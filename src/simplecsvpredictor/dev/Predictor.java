package simplecsvpredictor.dev;

import java.util.ArrayList;
import java.util.List;

public abstract class Predictor implements IModel {

    private List<Double> x;
    private List<Double> y;

    /**
     * Infer for every ranks.
     * 
     * @return Predicted outputs
     */
    public List<Double> predictAll() {
        List<Double> predictions = new ArrayList<Double>();
        for (int i = 0; i < y.size(); i++) {
            predictions.add(predict(i));
        }
        return predictions;
    }

    /**
     * Infer next value.
     * 
     * @return Predicted value
     */
    public Double predictNext() {
        Double x = 0.0;
        if (getX() != null && getX(getX().size() - 1) != null) {
            x = getX(getX().size() - 1) + 1;
        } else if (getY() != null) {
            x = (double) getY().size();
        }
        return predict(x);
    }

    public abstract Double predict(Double x);

    /**
     * Infer next value.
     * 
     * @return Predicted value
     */
    public List<Double> predictNext(int n) {
        List<Double> predictions = new ArrayList<Double>();
        for (int i = 0; i < n; i++) {
            predictions.add(predict(getY().size() + i));
        }
        return predictions;
    }

    /**
     * Get the x value or i if undefined.
     * 
     * @param i
     * @return
     */
    public Double getX(int i) {
        double value = i;
        // if x is defined and value exists
        if (x != null && !x.isEmpty() && x.get(i) != null) {
            value = x.get(i);
        }
        return value;
    }

    /**
     * Get the y value at i rank
     * 
     * @param i
     *            rank
     * @return the value
     */
    public Double getY(int i) {
        return y.get(i);
    }

    /**
     * Get missing ranks from a list.
     * 
     * @param values
     *            The list
     * @return missing ranks
     */
    public List<Integer> getMissingValuesRanks(List<Double> values) {
        List<Integer> ranks = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i) == null) {
                ranks.add(i);
            }
        }
        return ranks;
    }

    /**
     * @return The score of the model
     */
    public Double evaluate() {
        Double score = 0.0;
        Double deltaPred = 0.0;
        if (getY().size() > 0) {
            for (int i = 0; i < getY().size(); i++) {
                if (getY(i) != 0) {
                    deltaPred += normalize(abs((predict(getX(i)) / getY(i))));
                } else {
                    deltaPred += normalize(abs(predict(getX(i)) - getY(i)));
                }
            }

            score = deltaPred / getY().size();
        }
        return score;
    }

    /**
     * Get absolute value of x.
     * 
     * @param x
     * @return Absolute value of x
     */
    private Double abs(Double x) {
        Double y = x;
        if (x < 0)
            y = x * -1;

        return y;
    }

    /**
     * Get normalized value of x.
     * 
     * @param x
     * @return normalized value of x
     */
    private Double normalize(Double x) {
        Double y = x;
        if (x < 0) {
            y = 0.0;
        } else if (x > 1) {
            y = 0.0;
        }

        return y;
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
