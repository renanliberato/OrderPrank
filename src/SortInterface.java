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
            "Selection - double",
            "Bubble - double",
            "Insertion - double",
            "QuickSort - double",
            "ShellSort - double",
            "Selection - String",
            "Bubble - String",
            "Insertion - String",
            "QuickSort - String",
            "ShellSort - String"
    };
    // jcbMetodos.getSelectedItem()
    private JFileChooser arquivo;

    private long dataInicial,dataFinal;

    private String caminhoArquivo, diretorio;

    public SortInterface()
    {
        caminhoArquivo = "";

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
            SortManager sortManager = new SortManager();

            String methodParam = (String)jcbMetodos.getSelectedItem();

            sortManager.setMethod(methodParam.split(" "));
            sortManager.start(caminhoArquivo, diretorio);

            jtaSaida.setText("Método:" + (String)jcbMetodos.getSelectedItem() + "\n" + "Tempo de execução: " + (sortManager.getDataFinal() - sortManager.getDataInicial())/1000 + " segundos");
            jtaSaida.enable(false);
        }
        if (e.getSource() == jbSair) {
            System.exit(0);
        }
    }

    public static void main(String args[])
    {
        SortInterface cg = new SortInterface();
    }
}
