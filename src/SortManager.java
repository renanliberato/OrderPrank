/**
 * Created by renan on 08/05/2016.
 */
public class SortManager extends Sort
{
    /**
     * Valores em milisegundos gerados antes de depois da execução do algoritmo de ordenação.
     */
    private long dataInicial, dataFinal;

    /**
     * Vetor de comprimento 3 gerado do seletor de métodos da interface.
     */
    private String[] method;

    /**
     * Variável onde é armazenado o vetor de strings durante o processo de ordenação.
     */
    private String[] stringList;

    /**
     * Variável onde é armazenado o vetor de doubles durante o processo de ordenação.
     */
    private double[] doubleList;

    /**
     * Objeto da classe FileManager utilizado na manipulação de arquivos.
     */
    FileManager fileManager;

    /**
     * Método construtor responsável por instanciar uma dependência, a classe FileManager.
     */
    public SortManager()
    {
        this.fileManager = new FileManager();
    }

    /**
     * Método responsável por gerenciar a execução das ordenações.
     * @param file Caminho do arquivo desordenado.
     * @param filePath Caminho da pasta onde se encontra o arquivo desordenado.
     */
    public void start(String file, String filePath)
    {
        fileManager.setFile(file, filePath);
        String methodToCompare = method[2];
        if (methodToCompare.compareTo("double") == 0) {
            this.doubleList = fileManager.readDouble();
            sortDouble();
            fileManager.recordDouble(this.doubleList);
        } else if (methodToCompare.compareTo("String") == 0) {
            this.stringList = fileManager.readString();
            sortString();
            fileManager.recordString(stringList);
        }
        fileManager.recordLog(this.dataInicial, this.dataFinal);
    }

    /**
     * Método responsável por encaminhar o vetor  de double desordenado ao método de ordenação selecionado pelo usuário.
     */
    public void sortDouble()
    {
        dataInicial = System.currentTimeMillis();

        switch (this.method[0]) {
            case "Selection":
                this.doubleList = selection(this.doubleList);
                break;
            case "Bubble":
                this.doubleList = bubble(this.doubleList);
                break;
            case "Insertion":
                this.doubleList = insertion(this.doubleList);
                break;
            case "QuickSort":
                this.doubleList = quickSort(this.doubleList, 0, this.doubleList.length-1);
                break;
            case "ShellSort":
                this.doubleList = shellSort(this.doubleList);
                break;
        }
        dataFinal = System.currentTimeMillis();
    }

    /**
     * Método responsável por encaminhar o vetor  de Strings desordenado ao método de ordenação selecionado pelo usuário.
     */
    public void sortString()
    {
        dataInicial = System.currentTimeMillis();

        switch (this.method[0]) {
            case "Selection":
                this.stringList = selection(this.stringList);
                break;
            case "Bubble":
                this.stringList = bubble(this.stringList);
                break;
            case "Insertion":
                this.stringList = insertion(this.stringList);
                break;
            case "QuickSort":
                this.stringList = quickSort(this.stringList, 0, this.stringList.length-1);
                break;
            case "ShellSort":
                this.stringList = shellSort(this.stringList);
                break;
        }
        dataFinal = System.currentTimeMillis();
    }

    /**
     * Getter de dataInicial
     * @return retorna um valor em milisegundos
     */
    public long getDataInicial()
    {
        return dataInicial;
    }

    /**
     * Setter de dataInicial
     * @param dataInicial valor em milisegundos a ser armazenado
     */
    public void setDataInicial(long dataInicial)
    {
        this.dataInicial = dataInicial;
    }

    /**
     * Getter de dataFinal
     * @return retorna um valor em milisegundos
     */
    public long getDataFinal() {
        return dataFinal;
    }

    /**
     * Setter de dataFinal
     * @param dataFinal valor em milisegundos a ser armazenado
     */
    public void setDataFinal(long dataFinal)
    {
        this.dataFinal = dataFinal;
    }

    /**
     * Getter do vetor de Strings gerado do seletor de métodos da interface.
     * @return Retorna um vetor de comprimento 3.
     */
    public String[] getMethod()
    {
        return method;
    }

    /**
     * Setter do vetor de Strings gerado do seletor de métodos da interface.
     * @param method Vetor de Strings de comprimento 3.
     */
    public void setMethod(String[] method)
    {
        this.method = method;
        fileManager.setMethod(method);
    }
}
