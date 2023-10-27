import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {
    private JLabel title;
    private JLabel cpfLabel;
    private JTextField cpfField;
    private JLabel birthDateLabel;
    private JTextField birthDateField;
    private JButton loginButton;
    private JButton backButton;

    public TelaLogin() {
        initComponents();
    }

    private void initComponents() {
        // Configurações da janela
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela de Login");
        setPreferredSize(new Dimension(500, 500));
        getContentPane().setBackground(Color.decode("#ffffff"));

        // Título da aplicação
        title = criarLabel("JavaBNB", 24, Color.decode("#000000"));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        // Campos e rótulos para o formulário de login
        cpfLabel = criarLabel("CPF:", 12, Color.BLACK);
        cpfField = new JTextField();
        birthDateLabel = criarLabel("Data de Nascimento:", 12, Color.BLACK);
        birthDateField = new JTextField();

        loginButton = criarBotao("Login");
        loginButton.addActionListener(e -> loginButtonActionPerformed(e));

        // Botão para voltar
        backButton = criarBotao("Voltar");
        backButton.addActionListener(e -> voltarParaTelaInicial());

        // Configuração do layout usando GroupLayout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        // Adiciona componentes ao layout
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addComponent(title)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(cpfLabel)
                    .addComponent(cpfField)
                    .addComponent(birthDateLabel)
                    .addComponent(birthDateField)
                    .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(title)
                .addGap(20)
                .addComponent(cpfLabel)
                .addComponent(cpfField, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addGap(18)
                .addComponent(birthDateLabel)
                .addComponent(birthDateField, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addGap(50)
                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addGap(10)
                .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addGap(50)
        );

        pack();
        setLocationRelativeTo(null); // Isso centraliza a janela no meio da tela
    }

    private void loginButtonActionPerformed(ActionEvent evt) {
        // Implementação da lógica de login
        String cpf = cpfField.getText();
        String birthDate = birthDateField.getText();

        // Verifica as credenciais e acessa o sistema
        if (cpf.equals("seuCpf") && birthDate.equals("suaDataDeNascimento")) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
        } else {
            JOptionPane.showMessageDialog(this, "Login falhou. Verifique suas credenciais.");
        }
    }

    private void voltarParaTelaInicial() {
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
        dispose(); // Fecha a tela de login
    }

    public static void main(String args[]) {
        // Configuração do Look and Feel do Swing
        configurarLookAndFeel();

        // Cria uma instância da classe TelaLogin e a torna visível
        java.awt.EventQueue.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }

    private static void configurarLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private JLabel criarLabel(String texto, int fontSize, Color cor) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Serif", Font.BOLD, fontSize));
        label.setForeground(cor);
        return label;
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(200, 50));
        botao.setFont(new Font("Arial", Font.BOLD, 16));
        return botao;
    }
}
