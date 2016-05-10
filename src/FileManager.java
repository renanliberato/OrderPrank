import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by renan on 08/05/2016.
 */
public class FileManager
{
    private String file, filePath;

    private String[] method;

    private FileReader fileReader;

    private int max;

    private BufferedReader in;


    public void setFile(String file, String filePath)
    {
        this.file = file;
        this.filePath = filePath;
        try {
            this.fileReader = new FileReader(file);

            this.in = new BufferedReader(this.fileReader);
        } catch(IOException e){
            System.out.println("Erro de leitura");
        }

    }

    public void setMethod(String[] method)
    {
        this.method = method;
    }

    public double[] readDouble()
    {

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

    public String[] readString()
    {
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

    public void recordDouble(double doubleList[])
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

    public void recordLog(long dataInicial, long dataFinal)
    {
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
            valor += (dataFinal - dataInicial);
            valor += new SimpleDateFormat("yyy/MM/dd - HH:mm:ss").format(Calendar.getInstance().getTime()) + " \n";
            out.write(valor.getBytes());
            out.close();
        }
        catch (IOException e) {
            System.out.println("Erro na gravação do arquivo");
        }
    }
}