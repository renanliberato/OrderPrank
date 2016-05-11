import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by renan on 08/05/2016.
 */
public class FileManager
{
    /**
     * Atributo para armazenamento do cmainho do arquivo a ser ordenado e de sua pasta,
     * vindos do SortManager.
     */
    private String file, filePath;

    /**
     * Vetor de métodos vindo de SortManager.
     */
    private String[] method;

    /**
     * Objeto utilizado para a leitura de arquivos.
     */
    private FileReader fileReader;

    /**
     * Valor gerado nos método de leitura readDouble e readString.
     * Delimitador do tamanho do vetor de números desordenados.
     */
    private int max;

    /**
     * Objeto utilizado durante a leitura do arquivo
     */
    private BufferedReader in;


    /**
     * Método criado para armazenar as dependências da classe.
     * @param file caminho do arquivo a ser ordenado
     * @param filePath pasta onde o arquivo a ser ordenado está.
     */
    public void setFile(String file, String filePath) {
        this.file = file;
        this.filePath = filePath;

        try {
            this.fileReader = new FileReader(file);
            this.in = new BufferedReader(this.fileReader);

        } catch(IOException e){

            System.out.println("Erro de leitura");
        }

    }

    /**
     * Setter do vetor do método selecionado.
     * @param method
     */
    public void setMethod(String[] method) {
        this.method = method;
    }

    /**
     * Método responsável por ler o arquivo com dados double desordenados.
     * @return Retorna um vetor de doubles de tamanho this.max desordenados.
     */
    public double[] readDouble() {
        double[] doubleList =  new double[0];

        try {
            max = Integer.parseInt(this.in.readLine());
            doubleList = new double[max];

            int i = 0;

            while (this.in.ready()) {
                doubleList[i] = Double.parseDouble(in.readLine());
                i++;
            }

            this.in.close();

        } catch (IOException e) {

            System.out.println("Erro na leitura do arquivo");
        }

        return doubleList;
    }

    /**
     * Método responsável por ler o arquivo com dados String desordenados.
     * @return Retorna um vetor de Strings de tamanho this.max desordenados.
     */
    public String[] readString() {
        String[] stringList = new String[0];
        try {
            max = Integer.parseInt(this.in.readLine());
            stringList = new String[max];

            int i = 0;

            while (this.in.ready()) {
                stringList[i] = in.readLine();
                i++;
            }

            this.in.close();

        } catch (IOException e) {

            System.out.println("Erro na leitura do arquivo");
        }

        return stringList;
    }

    /**
     * Método responsável por gravar o arquivo de doubles ordenados.
     * @param doubleList Lista de doubles ordenados para gravação no arquivo.
     */
    public void recordDouble(double doubleList[]) {
        String valor="", arquivoSaida="";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        try {
            String methodName = this.method[0] + "_" + this.method[2];
            this.filePath = this.filePath.replace("\\", "/");
            arquivoSaida = this.filePath + "/result/" + timeStamp + "_" + methodName + ".txt";
            File saida;
            saida = new File(arquivoSaida);
            FileOutputStream out = new FileOutputStream(saida);

            for (int j = 0; j < max; j++) {
                valor = String.valueOf(doubleList[j]) + " \n";

                out.write(valor.getBytes());
            }

            out.close();
        }

        catch (IOException e) {
            System.out.println("Erro na gravação do arquivo");
        }
    }

    /**
     * Método responsável por gravar o arquivo de Strings ordenadas.
     * @param stringList Lista de Strings ordenadas para gravação no arquivo.
     */
    public void recordString(String stringList[])
    {
        String valor="", arquivoSaida="";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        try {
            String methodName = this.method[0] + "_" + this.method[2];
            this.filePath = this.filePath.replace("\\", "/");
            arquivoSaida = this.filePath + "/result/" + timeStamp + "_" + methodName + ".txt";
            File saida;
            saida = new File(arquivoSaida);
            FileOutputStream out = new FileOutputStream(saida);

            for (int j=0; j<max; j++) {
                valor = String.valueOf(stringList[j]) + "\n";

                out.write(valor.getBytes());
            }

            out.close();
        }

        catch (IOException e) {
            System.out.println("Erro na gravação do arquivo");
        }
    }

    /**
     * Método repsonsável por gerar o relatório de log resultante da ordenação no arquivo sortLogCSV.csv.
     * @param dataInicial Valor em milisegundos gerado antes da execução do algoritmo de ordenação.
     * @param dataFinal Valor em milisegundos gerado depois da execução do algoritmo de ordenação.
     */
    public void recordLog(long dataInicial, long dataFinal) {
        String valor="", arquivoSaida="", arquivoResult = "";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        try {
            String methodName = this.method[0] + "_" + this.method[2];
            arquivoResult = timeStamp + "_" + methodName + ".txt";
            this.filePath = this.filePath.replace("\\", "/");
            arquivoSaida = this.filePath + "/sortLogCSV.csv";
            File saida;
            saida = new File(arquivoSaida);
            FileOutputStream out = new FileOutputStream(saida, true);
            valor  = this.file + ",";
            valor += arquivoResult + ",";
            valor += methodName + ",";
            valor += dataFinal + ",";
            valor += dataInicial + ",";
            valor += (dataFinal - dataInicial) + ",";
            valor += new SimpleDateFormat("yyy/MM/dd - HH:mm:ss").format(Calendar.getInstance().getTime()) + " \n";

            out.write(valor.getBytes());

            out.close();
        }

        catch (IOException e) {

            System.out.println("Erro na gravação do arquivo");
        }
    }
}