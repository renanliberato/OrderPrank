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
    /**
     * Grids de layout
     */
    private JPanel p1,p2,p3;

    /**
     * Label dos botões da interface.
     */
    private JLabel jlMetodo,jlArquivo,jlSaida;

    /**
     * Botões da interface.
     */
    private JButton jbOrdenar,jbSair,jbArquivo;

    /**
     * Select box de métodos.
     */
    private JComboBox jcbMetodos;

    /**
     * Input do caminho do arquivo.
     */
    private JTextField jtfArquivo;

    /**
     * Textarea onde é exibido o resultado da ordenação.
     */
    private JTextArea jtaSaida;

    /**
     * Lista de métodos que aparecerão na interface para seleção.
     */
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
    /**
     * Variável resultante do arquivo a ser modificado.
     */
    private JFileChooser arquivo;

    /**
     * Variáveis que armazenam o caminho do arquivo e seu diretório.
     */
    private String caminhoArquivo, diretorio;

    /**
     * Método construtor que gera o layout na tela e inicializa seus componentes.
     */
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

    /**
     * Listener que é acionado ao pressionar algum botão da interface.
     * @param e evento gerado da ação do usuário, possuindo todos os recursos necessários para tomadas de ação.
     */
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
            for(int k = 0; k <= 50; k++)
                sortManager.start(caminhoArquivo, diretorio);

            jtaSaida.setText("Método:" + (String)jcbMetodos.getSelectedItem() + "\n" + "Tempo de execução: " + (sortManager.getDataFinal() - sortManager.getDataInicial())/1000 + " segundos");
            jtaSaida.enable(false);
        }
        if (e.getSource() == jbSair) {
            System.exit(0);
        }
    }

    /**
     * Método estático inicializador do programa, responsável por chamar o construtor da classe.
     * @param args argumentos opcionais.
     */
    public static void main(String args[])
    {
        SortInterface cg = new SortInterface();
    }
}
