import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // Define a cor de fundo
        getContentPane().setBackground(Color.decode("#ffffff"));

        // Título da aplicação
        title = new JLabel("JavaBNB");
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.decode("#000000"));

        // Campos e rótulos para o formulário de login
        cpfLabel = new JLabel("CPF:");
        cpfLabel.setFont(new Font("Arial", Font.BOLD, 12));
        cpfField = new JTextField();

        birthDateLabel = new JLabel("Data de Nascimento:");
        birthDateLabel.setFont(new Font("Arial", Font.BOLD, 12));
        birthDateField = new JTextField();

        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        // Botão para voltar
        backButton = new JButton("Voltar");

        // Define a ação do botão "Voltar"
        backButton.addActionListener(e -> {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.setVisible(true); // Exibe a tela inicial
            dispose(); // Fecha a tela de login
        });

        // Configuração do layout usando GroupLayout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(title)
                )
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(cpfLabel)
                        .addComponent(cpfField)
                        .addComponent(birthDateLabel)
                        .addComponent(birthDateField)
                        .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    )
                    .addGap(20, 20, 20)
                )
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(title)
                    .addGap(20, 20, 20)
                    .addComponent(cpfLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(cpfField, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addGap(18, 18, 18)
                    .addComponent(birthDateLabel)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(birthDateField, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addGap(50, 50, 50)
                    .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addGap(50, 50, 50)
                )
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

    public static void main(String args[]) {
        // Configuração do Look and Feel do Swing
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Cria uma instância da classe TelaLogin e a torna visível
        java.awt.EventQueue.invokeLater(() -> {
            new TelaLogin().setVisible(true);
        });
    }
}
