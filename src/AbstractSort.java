/**
 * Created by renan on 07/05/2016.
 */
public abstract class AbstractSort
{
    public abstract double[] selection(double[] list);

    public abstract String[] selection(String[] list);

    public abstract double[] bubble(double[] list);

    public abstract String[] bubble(String[] list);

    public abstract double[] insertion(double[] list);

    public abstract String[] insertion(String[] list);

    public abstract double[] quickSort(double[] list, int low, int high);

    public abstract String[] quickSort(String[] list, int low, int high);

    public abstract double[] shellSort(double[] list);

    public abstract String[] shellSort(String[] list);
}
