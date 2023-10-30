import javax.swing.*;
import java.awt.*;
public class ConfirmarReserva extends JFrame {
    private JLabel titleLabel;
    private JLabel quantidadeDiariasLabel;
    private JTextField quantidadeDiariasField;
    private JLabel precoDiariaLabel;
    private JLabel precoDiariaValueLabel;
    private JLabel precoTotalLabel;
    private JLabel precoTotalValueLabel;
    private JButton confirmarPagamentoButton;
    private JButton backButton;

    public ConfirmarReserva(String roomName, double diariaPrice) {
        initComponents(roomName, diariaPrice);
    }

    private void initComponents(String roomName, double diariaPrice) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Confirmar Reserva");
        setPreferredSize(new Dimension(400, 300));

        // Título da reserva
        titleLabel = criarLabel("Reserva do " + roomName, 18, Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);

        quantidadeDiariasLabel = criarLabel("Quantidade de Diárias", 12, Color.BLACK);
        quantidadeDiariasField = new JTextField(5);
        quantidadeDiariasField.addActionListener(e -> calcularPrecoTotal());

        precoDiariaLabel = criarLabel("Preço Diária", 12, Color.BLACK);
        precoDiariaValueLabel = criarLabel("$" + diariaPrice, 12, Color.BLACK);

        precoTotalLabel = criarLabel("Preço Total", 12, Color.BLACK);
        precoTotalValueLabel = criarLabel("", 12, Color.BLACK);

        // Botão "Confirmar Pagamento"
        confirmarPagamentoButton = criarBotao("Confirmar Pagamento");
        confirmarPagamentoButton.addActionListener(e -> abrirTelaPagamento());

        // Botão "Voltar"
        backButton = criarBotao("Voltar");
        backButton.addActionListener(e -> abrirTelaQuartos());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(titleLabel)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(quantidadeDiariasLabel)
                    .addComponent(quantidadeDiariasField)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(precoDiariaLabel)
                    .addComponent(precoDiariaValueLabel)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(precoTotalLabel)
                    .addComponent(precoTotalValueLabel)
                )
                .addComponent(confirmarPagamentoButton)
                .addComponent(backButton)
        );

        layout.linkSize(SwingConstants.HORIZONTAL, quantidadeDiariasLabel, precoDiariaLabel, precoTotalLabel);

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(titleLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(quantidadeDiariasLabel)
                    .addComponent(quantidadeDiariasField)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(precoDiariaLabel)
                    .addComponent(precoDiariaValueLabel)
                )
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(precoTotalLabel)
                    .addComponent(precoTotalValueLabel)
                )
                .addComponent(confirmarPagamentoButton)
                .addComponent(backButton)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void calcularPrecoTotal() {
        try {
            int quantidadeDiarias = Integer.parseInt(quantidadeDiariasField.getText());
            double diariaPrice = Double.parseDouble(precoDiariaValueLabel.getText().replace("$", ""));
            double precoTotal = quantidadeDiarias * diariaPrice;
            precoTotalValueLabel.setText("$" + precoTotal);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void abrirTelaPagamento() {
        new Pagamento().setVisible(true);
        dispose();
    }

    private void abrirTelaQuartos() {
        new QuartosDisponiveis().setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new ConfirmarReserva("Nome do Quarto 1", 100.00).setVisible(true);
        });
    }

    private JLabel criarLabel(String texto, int fontSize, Color cor) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Serif", Font.BOLD, fontSize));
        label.setForeground(cor);
        return label;
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        return botao;
    }
}
