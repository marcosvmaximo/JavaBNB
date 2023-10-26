import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastro extends JFrame {
    // Componentes da tela de cadastro
    private JLabel title;
    private JLabel name;
    private JTextField nameField;
    private JLabel birthDate;
    private JTextField birthDateField;
    private JLabel phone;
    private JTextField phoneField;
    private JLabel cpf;
    private JTextField cpfField;
    private JButton signUpButton;
    private JButton backButton;

    // Construtor da classe TelaCadastro
    public TelaCadastro() {
        initComponents();
    }

    // Inicialização dos componentes da tela
    private void initComponents() {
        // Configurações da janela
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela de Cadastro");
        setPreferredSize(new Dimension(500, 500));

        // Define a cor de fundo
        getContentPane().setBackground(Color.decode("#ffffff"));

        // Título da aplicação
        title = new JLabel("JavaBNB");
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.decode("#000000")); // Cor do texto

        // Campos e rótulos para o formulário de cadastro
        name = new JLabel("DIGITE O SEU NOME COMPLETO:");
        name.setFont(new Font("Arial", Font.BOLD, 12));
        nameField = new JTextField();

        birthDate = new JLabel("DIGITE SUA DATA DE NASCIMENTO:");
        birthDate.setFont(new Font("Arial", Font.BOLD, 12));
        birthDateField = new JTextField();

        phone = new JLabel("DIGITE O SEU TELEFONE:");
        phone.setFont(new Font("Arial", Font.BOLD, 12));
        phoneField = new JTextField();

        cpf = new JLabel("DIGITE O SEU CPF:");
        cpf.setFont(new Font("Arial", Font.BOLD, 12));
        cpfField = new JTextField();

        signUpButton = new JButton("CADASTRAR");

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                signUpButtonActionPerformed(evt);
            }
        });

        backButton = new JButton("Voltar");

        // Define a ação do botão "Voltar"
        backButton.addActionListener(e -> {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.setVisible(true); 
            dispose(); // Fecha a tela de cadastro
        });

        // Configuração do layout usando GroupLayout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)  // Espaço à esquerda
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(title)
                    .addComponent(name)
                    .addComponent(nameField)
                    .addComponent(birthDate)
                    .addComponent(birthDateField)
                    .addComponent(phone)
                    .addComponent(phoneField)
                    .addComponent(cpf)
                    .addComponent(cpfField)
                    .addComponent(signUpButton)
                    .addComponent(backButton)
                )
                .addContainerGap(50, Short.MAX_VALUE)  // Espaço à direita
            )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addContainerGap(50, Short.MAX_VALUE)  // Espaço acima
            .addComponent(title)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(name)
            .addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(birthDate)
            .addComponent(birthDateField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(phone)
            .addComponent(phoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(cpf)
            .addComponent(cpfField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(signUpButton)
            .addContainerGap(50, Short.MAX_VALUE)  // Espaço abaixo
            .addComponent(backButton)
            .addContainerGap(50, Short.MAX_VALUE)  // Espaço abaixo
        );

        pack();
        setLocationRelativeTo(null); // Isso centraliza a janela no meio da tela
    }

    // Método a ser implementado para tratar o evento do botão "CADASTRAR"
    private void signUpButtonActionPerformed(ActionEvent evt) {
        // Método de tratamento do evento do botão (a ser implementado)
    }

    // Método principal
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
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Cria uma instância da classe TelaCadastro e a torna visível
        java.awt.EventQueue.invokeLater(() -> {
            new TelaCadastro().setVisible(true);
        });
    }
}
