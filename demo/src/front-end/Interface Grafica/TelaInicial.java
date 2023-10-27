import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {
    private JButton botaoLogin;
    private JButton botaoCadastro;

    public TelaInicial() {
        // Configura a janela principal
        setTitle("Tela Inicial");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents(); // Inicializa os componentes da tela
    }

    private void initComponents() {
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(new BorderLayout());

        // Cria o painel superior com o título
        JPanel painelSuperior = criarPainel(Color.WHITE, new FlowLayout(FlowLayout.CENTER, 0, 100));
        JLabel label = criarLabel("JavaBNB", 24, Color.BLACK);
        painelSuperior.add(label);

        // Cria o painel inferior com botões
        JPanel painelInferior = criarPainel(Color.WHITE, new FlowLayout(FlowLayout.CENTER, 0, 50));
        botaoLogin = criarBotao("Login");
        botaoCadastro = criarBotao("Cadastro");
        painelInferior.add(botaoLogin);
        painelInferior.add(botaoCadastro);

        // Adiciona os painéis ao conteúdo
        contentPane.add(painelSuperior, BorderLayout.NORTH);
        contentPane.add(painelInferior, BorderLayout.CENTER);

        // Configura ação dos botões
        botaoLogin.addActionListener(e -> mostrarTelaLogin());
        botaoCadastro.addActionListener(e -> mostrarTelaCadastro());
    }

    private JPanel criarPainel(Color background, LayoutManager layout) {
        JPanel painel = new JPanel();
        painel.setBackground(background);
        painel.setLayout(layout);
        return painel;
    }

    private JLabel criarLabel(String texto, int fontSize, Color cor) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Serif", Font.BOLD, fontSize));
        label.setForeground(cor);
        return label;
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(150, 40));
        botao.setForeground(Color.BLACK);
        botao.setBackground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.BOLD, 16));
        return botao;
    }

    private void mostrarTelaLogin() {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
        this.setVisible(false);
    }

    private void mostrarTelaCadastro() {
        TelaCadastro telaCadastro = new TelaCadastro();
        telaCadastro.setVisible(true);
        this.setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.setVisible(true);
        });
    }
}
