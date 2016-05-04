import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class ClassificacaoGrafica extends JFrame implements ActionListener
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
            "Seleção - String",
            "Bolha - double",
            "Bolha - String",
            "Inserção - double",
            "Inserção - String",
            "QuickSort - double",
            "QuickSort - String",
            "ShellSort - double",
            "ShellSort - String"
    };

    private JFileChooser arquivo;

    private String caminhoArquivo, diretorio;

    private long dataInicial,dataFinal;

    private int max;

    //Construtor da classe - irá inicializar os objetos e variáveis de instãncia
    public ClassificacaoGrafica()
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

    //Este método controla todos os eventos de botões
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

    //Este método realiaza a leitura do arquivo e realiza a atribuição ao vetor a ser classificado
    public void leitura(String arquivo, String diretorio)
    {
        Classificacao c = new Classificacao();
        ClassificacaoStrings cStrings = new ClassificacaoStrings();
        int metodo, i;
        double vet[];
        Scanner input = new Scanner(System.in);
        Date data = new Date();

        try {
            BufferedReader in = new BufferedReader(new FileReader(arquivo));

            max = Integer.parseInt(in.readLine());
            vet = new double[max];
            i   = 0;

            while (in.ready()) {
                vet[i]=Double.parseDouble(in.readLine());
                i++;
            }
            in.close();

            //Neste ponto voces deverâo acrescentar novos itens case para outros métodos de ordenação
            switch(jcbMetodos.getSelectedIndex()){
                case 0:
                    dataInicial = System.currentTimeMillis();
                    vet = c.selecao(vet);
                    dataFinal = System.currentTimeMillis();
                    break;
                case 1:
                    dataInicial = System.currentTimeMillis();
                    vet = cStrings.selecao(vet);
                    dataFinal = System.currentTimeMillis();
                    break;
                case 2:
                    dataInicial = System.currentTimeMillis();
                    vet = c.bubble(vet);
                    dataFinal = System.currentTimeMillis();
                    break;
                case 4:
                    dataInicial = System.currentTimeMillis();
                    vet = cStrings.bubble(vet);
                    dataFinal = System.currentTimeMillis();
                    break;
                case 5:
                    dataInicial = System.currentTimeMillis();
                    vet = c.insercao(vet);
                    dataFinal = System.currentTimeMillis();
                    break;
                case 6:
                    dataInicial = System.currentTimeMillis();
                    vet = cStrings.insercao(vet);
                    dataFinal = System.currentTimeMillis();
                    break;
                case 7:
                    dataInicial = System.currentTimeMillis();
                    vet = c.quickSort(vet, 0, vet.length-1);
                    dataFinal = System.currentTimeMillis();
                    break;
                case 8:
                    dataInicial = System.currentTimeMillis();
                    vet = cStrings.quickSort(vet, 0, vet.length-1);
                    dataFinal = System.currentTimeMillis();
                    break;
                case 9:
                    dataInicial = System.currentTimeMillis();
                    vet = c.shellSort(vet);
                    dataFinal = System.currentTimeMillis();
                    break;
                case 10:
                    dataInicial = System.currentTimeMillis();
                    vet = cStrings.shellSort(vet);
                    dataFinal = System.currentTimeMillis();
                    break;
                default:
                    System.out.println("Opção Inválida");
                    break;
            }
            in.close();
            gravacao(vet);
        } catch (IOException e) {
            System.out.println("Erro na gravação do arquivo");
        }
    }

    //Este método realiaza a gravação do arquivo de saida
    public void gravacao(double vet[])
    {
        String valor="", arquivoSaida="";

        try {
            arquivoSaida = (String)jcbMetodos.getSelectedItem();

            diretorio = diretorio.replace("\\", "/");
            arquivoSaida = diretorio + "/" + arquivoSaida + ".txt";
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
        ClassificacaoGrafica cg = new ClassificacaoGrafica();
    }
}
