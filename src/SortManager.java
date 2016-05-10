/**
 * Created by renan on 08/05/2016.
 */
public class SortManager extends Sort
{
    private long dataInicial, dataFinal;

    private String[] method;

    private String[] stringList;

    private double[] doubleList;

    FileManager fileManager;

    public SortManager()
    {
        this.fileManager = new FileManager();
    }

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

    public long getDataInicial()
    {
        return dataInicial;
    }

    public void setDataInicial(long dataInicial)
    {
        this.dataInicial = dataInicial;
    }

    public long getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(long dataFinal)
    {
        this.dataFinal = dataFinal;
    }

    public String[] getMethod()
    {
        return method;
    }

    public void setMethod(String[] method)
    {
        this.method = method;
        fileManager.setMethod(method);
    }
}
