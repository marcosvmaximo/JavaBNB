import services.LodgeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelaCadastro extends JFrame {
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

    public TelaCadastro() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tela de Cadastro");
        setPreferredSize(new Dimension(500, 500));
        getContentPane().setBackground(Color.decode("#ffffff"));

        title = criarLabel("JavaBNB", 24, Color.decode("#000000"));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        name = criarLabel("DIGITE O SEU NOME COMPLETO:", 12, Color.BLACK);
        nameField = criarTextField();

        birthDate = criarLabel("DIGITE SUA DATA DE NASCIMENTO:", 12, Color.BLACK);
        birthDateField = criarTextField();

        phone = criarLabel("DIGITE O SEU TELEFONE:", 12, Color.BLACK);
        phoneField = criarTextField();

        cpf = criarLabel("DIGITE O SEU CPF:", 12, Color.BLACK);
        cpfField = criarTextField();

        signUpButton = criarBotao("CADASTRAR");
        signUpButton.addActionListener(e -> signUpButtonActionPerformed(e));

        backButton = criarBotao("Voltar");
        backButton.addActionListener(e -> voltarParaTelaInicial());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
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
                .addContainerGap(50, Short.MAX_VALUE)
            )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
            .addContainerGap(50, Short.MAX_VALUE)
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
            .addContainerGap(50, Short.MAX_VALUE)
            .addComponent(backButton)
            .addContainerGap(50, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void signUpButtonActionPerformed(ActionEvent evt) {

        // Obter os dados do front-end
        String nomeCompleto = nameField.getText();
        String dataNascimentoStr = birthDateField.getText();
        String numeroTelefone = phoneField.getText();
        String cpf = cpfField.getText();
    
        // Verifique se os campos obrigatórios estão preenchidos
        if (nomeCompleto.isEmpty() || numeroTelefone.isEmpty() || cpf.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios.", "Erro", JOptionPane.ERROR_MESSAGE);

        } else {

            Date dataNascimento = null;
            try {
                // Tenta converter a data de nascimento em um objeto Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                dataNascimento = dateFormat.parse(dataNascimentoStr);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Data de nascimento inválida. Use o formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
    
            if (dataNascimento != null) {
                // Chame a função RegisterUser
                LodgeService service = new LodgeService();
                
                Boolean cadastrado = service.RegisterUser(nomeCompleto, dataNascimentoStr, numeroTelefone, cpf);
    
                if (cadastrado) {
                    JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    // Limpe os campos após o cadastro
                    nameField.setText("");
                    birthDateField.setText("");
                    phoneField.setText("");
                    cpfField.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro no cadastro. Verifique os dados e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }    

    private void voltarParaTelaInicial() {
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        configurarLookAndFeel();

        java.awt.EventQueue.invokeLater(() -> {
            new TelaCadastro().setVisible(true);
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

    private JTextField criarTextField() {
        JTextField textField = new JTextField();
        return textField;
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(200, 50));
        return botao;
    }
}
