import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SortInterface extends JFrame implements ActionListener
{
    //Classe Gráfica - declaração dos componentes (objetos) e variáveis de instãncia
    private JPanel p1,p2,p3;

    private JLabel jlMetodo,jlArquivo,jlSaida;

    private JComboBox jcbMetodos;

    private JTextField jtfArquivo;

    private JTextArea jtaSaida;

    private JButton jbOrdenar,jbSair,jbArquivo;

    // Nesse ponto vocês deverão acrescentar novos métodos
    private String[] metodos = {
            "Seleção - double",
            "Bolha - double",
            "Inserção - double",
            "QuickSort - double",
            "ShellSort - double",
            "Seleção - String",
            "Bolha - String",
            "Inserção - String",
            "QuickSort - String",
            "ShellSort - String"
    };
    // jcbMetodos.getSelectedItem()
    private JFileChooser arquivo;

    private String caminhoArquivo, diretorio;

    private long dataInicial,dataFinal;

    private int max;

    private String method;

    private String[] vet;

    private double[] vetDouble;

    private FileReader fileReader;

    private BufferedReader in;

    private Sort c;

    public SortInterface()
    {
        caminhoArquivo="";

        p1 = new JPanel(new GridLayout(4,2));
        p2 = new JPanel();
        p3 = new JPanel(new BorderLayout());

        jlMetodo   = new JLabel("Método");
        jlArquivo  = new JLabel("Arquivo");
        jlSaida    = new JLabel("Saidas");
        jcbMetodos = new JComboBox(metodos);

        jtfArquivo = new JTextField(25);
        jtfArquivo.disable();

        arquivo = new JFileChooser();

        jtaSaida = new JTextArea(2,2);

        jbOrdenar = new JButton("Ordenar");
        jbSair    = new JButton("Sair");
        jbArquivo = new JButton("...");

        p1.add(jlMetodo);
        p1.add(jcbMetodos);
        p1.add(jlSaida);
        p1.add(jtaSaida);
        p1.add(jbOrdenar);
        p1.add(jbSair);
        p2.add(jlArquivo);
        p2.add(jtfArquivo);
        p2.add(jbArquivo);
        p3.add("North", p2);
        p3.add("Center", p1);
        jbArquivo.addActionListener(this);
        jbOrdenar.addActionListener(this);
        jbSair.addActionListener(this);
        getContentPane().add(p3);
        setTitle("Métodos de Ordenação");
        setSize(400, 200);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == jbArquivo) {
            arquivo.showOpenDialog(null);

            caminhoArquivo = arquivo.getSelectedFile().getAbsolutePath();
            diretorio      = arquivo.getCurrentDirectory().getPath();
            caminhoArquivo = caminhoArquivo.replace("\\", "/");

            jtfArquivo.setText(caminhoArquivo);
        }
        if (e.getSource() == jbOrdenar) {
            leitura(caminhoArquivo,diretorio);
        }
        if (e.getSource() == jbSair) {
            System.exit(0);
        }
    }

    public void leitura(String arquivo, String diretorio)
    {
        c = new Sort();
        int i;
        String[] vet;
        double[] vetDouble;
        String method = "";

        try {
            this.fileReader = new FileReader(arquivo);

            this.in = new BufferedReader(this.fileReader);

            max = Integer.parseInt(this.in.readLine());
            this.vet = new String[max];
            this.vetDouble = new double[max];

            if (this.jcbMetodos.getSelectedIndex() <= 4) {
                readListDouble();
            } else {
                readListString();
            }

        } catch (IOException e) {
            System.out.println("Erro na leitura do arquivo");
        }
    }

    public void readListDouble()
    {
        try {
            int i = 0;
            while (this.in.ready()) {
                vetDouble[i] = Double.parseDouble(in.readLine());
                i++;
            }
            this.in.close();
            sort(vetDouble);

        } catch(IOException e) {
            System.out.println("Erro na leitura do arquivo");
        }
    }

    public void readListString()
    {
        try {
            int i = 0;
            while (this.in.ready()) {
                vet[i] = in.readLine();
                i++;
            }
            this.in.close();
            sort(vet);

        } catch(IOException e) {
            System.out.println("Erro na leitura do arquivo");
        }
    }

    public void sort(double[] vetDouble)
    {
        dataInicial = System.currentTimeMillis();

        switch (jcbMetodos.getSelectedIndex()) {
            case 0:
                this.vetDouble = c.selection(vetDouble);
            case 1:
                this.vetDouble = c.bubble(vetDouble);
            case 2:
                this.vetDouble = c.insertion(vetDouble);
            case 3:
                this.vetDouble = c.quickSort(vetDouble, 0, vetDouble.length-1);
            case 4:
                this.vetDouble = c.shellSort(vetDouble);
        }
        dataFinal = System.currentTimeMillis();
            gravacao(this.vetDouble);
    }

    public void sort(String[] vet)
    {
        dataInicial = System.currentTimeMillis();

        switch (jcbMetodos.getSelectedIndex()) {
            case 5:
                this.vet = c.selection(vet);
            case 6:
                this.vet = c.bubble(vet);
            case 7:
                this.vet = c.insertion(vet);
            case 8:
                this.vet = c.quickSort(vet, 0, vet.length-1);
            case 9:
                this.vet = c.shellSort(vet);
        }
        dataFinal = System.currentTimeMillis();
        gravacao(vet);
    }

    public void gravacao(double vet[])
    {

        String valor="", arquivoSaida="";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        try {
            String methodName = (String)jcbMetodos.getSelectedItem();
            String[] methodNameArray = methodName.split(" ");
            methodName = methodNameArray[0] + "_" + methodNameArray[2];

            diretorio = diretorio.replace("\\", "/");
            arquivoSaida = diretorio + "/" + timeStamp + "_" + methodName + ".txt";
            File saida;
            saida = new File(arquivoSaida);
            FileOutputStream out = new FileOutputStream(saida);
            for (int j = 0; j < max; j++) {
                valor = String.valueOf(vet[j]) + " \n";
                out.write(valor.getBytes());
            }
            out.close();
        }
        catch (IOException e) {
            System.out.println("Erro na gravação do arquivo");
        }

        jtaSaida.setText("Método:" + (String)jcbMetodos.getSelectedItem() + "\n" + "Tempo de execução: " + (dataFinal - dataInicial)/1000 + " segundos");
        jtaSaida.enable(false);
    }

    public void gravacao(String vet[])
    {
        String valor="", arquivoSaida="";
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        try {
            String methodName = (String)jcbMetodos.getSelectedItem();
            String[] methodNameArray = methodName.split(" ");
            methodName = methodNameArray[0] + "_" + methodNameArray[2];

            diretorio = diretorio.replace("\\", "/");
            arquivoSaida = diretorio + "/" + timeStamp + "_" + methodName + ".txt";
            File saida;
            saida = new File(arquivoSaida);
            FileOutputStream out = new FileOutputStream(saida);
            for (int j=0; j<max; j++) {
                valor = String.valueOf(vet[j]) + "\n";
                out.write(valor.getBytes());
            }
            out.close();
        }
        catch (IOException e) {
            System.out.println("Erro na gravação do arquivo");
        }

        jtaSaida.setText("Método:" + (String)jcbMetodos.getSelectedItem() + "\n" + "Tempo de execução: " + (dataFinal - dataInicial)/1000 + " segundos");
        jtaSaida.enable(false);
    }

    public static void main(String args[])
    {
        SortInterface cg = new SortInterface();
    }
}
