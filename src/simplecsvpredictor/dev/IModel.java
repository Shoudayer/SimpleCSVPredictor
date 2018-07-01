package simplecsvpredictor.dev;

/**
 * Basic linear model interface.
 * 
 * @author Simon H.
 *
 */
public interface IModel {
    public void fit();

    public Double predict(int x);

    public Double evaluate();
}
