import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {
    private JButton botaoLogin;
    private JButton botaoCadastro;

    public TelaInicial() {
        setTitle("Tela Inicial");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        Container contentPane = getContentPane();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(new BorderLayout());

        JPanel painelSuperior = new JPanel();
        painelSuperior.setBackground(Color.WHITE);
        painelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));

        JLabel label = new JLabel("JavaBNB");
        label.setFont(new Font("Serif", Font.BOLD, 24));
        label.setForeground(Color.BLACK);

        painelSuperior.add(label);

        JPanel painelInferior = new JPanel();
        painelInferior.setBackground(Color.WHITE);
        painelInferior.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));

        botaoLogin = criarBotao("Login");
        botaoCadastro = criarBotao("Cadastro");

        painelInferior.add(botaoLogin);
        painelInferior.add(botaoCadastro);

        contentPane.add(painelSuperior, BorderLayout.NORTH);
        contentPane.add(painelInferior, BorderLayout.CENTER);

        botaoLogin.addActionListener(e -> mostrarTelaLogin());
        botaoCadastro.addActionListener(e -> mostrarTelaCadastro());
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
