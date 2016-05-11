/**
 * Created by renan on 07/05/2016.
 */
public abstract class AbstractSort
{
    /**
     * Método de ordenação de uma lista double através do algorítmo de seleção
     * @param list lista desordenada de valores double.
     * @return lista ordenada de valores double.
     */
    public abstract double[] selection(double[] list);

    /**
     * Método de ordenação de uma lista de Strings através do algorítmo de seleção
     * @param list lista desordenada de valores String.
     * @return lista ordenada de valores String.
     */
    public abstract String[] selection(String[] list);

    /**
     * Método de ordenação de uma lista double através do algorítmo bubbleSort
     * @param list lista desordenada de valores double.
     * @return lista ordenada de valores double.
     */
    public abstract double[] bubble(double[] list);

    /**
     * Método de ordenação de uma lista de Strings através do algorítmo bubbleSort
     * @param list lista desordenada de valores String.
     * @return lista ordenada de valores String.
     */
    public abstract String[] bubble(String[] list);

    /**
     * Método de ordenação de uma lista double através do algorítmo de inserção
     * @param list lista desordenada de valores double.
     * @return lista ordenada de valores double.
     */
    public abstract double[] insertion(double[] list);

    /**
     * Método de ordenação de uma lista de Strings através do algorítmo de inserção
     * @param list lista desordenada de valores String.
     * @return lista ordenada de valores String.
     */
    public abstract String[] insertion(String[] list);

    /**
     * Método de ordenação de uma lista double através do algorítmo de quickSort
     * @param list lista desordenada de valores double.
     * @return lista ordenada de valores double.
     */
    public abstract double[] quickSort(double[] list, int low, int high);

    /**
     * Método de ordenação de uma lista de Strings através do algorítmo quickSort
     * @param list lista desordenada de valores String.
     * @return lista ordenada de valores String.
     */
    public abstract String[] quickSort(String[] list, int low, int high);

    /**
     * Método de ordenação de uma lista double através do algorítmo shellSort
     * @param list lista desordenada de valores double.
     * @return lista ordenada de valores double.
     */
    public abstract double[] shellSort(double[] list);

    /**
     * Método de ordenação de uma lista de Strings através do algorítmo shellSort
     * @param list lista desordenada de valores String.
     * @return lista ordenada de valores String.
     */
    public abstract String[] shellSort(String[] list);
}