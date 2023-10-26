import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmarReserva extends JFrame {
    private JLabel titleLabel;
    private JLabel quantidadeDiariasLabel;
    private JTextField quantidadeDiariasField;
    private JLabel precoDiariaLabel;
    private JLabel precoDiariaValueLabel;
    private JLabel precoTotalLabel;
    private JLabel precoTotalValueLabel;
    private JButton confirmarPagamentoButton;

    public ConfirmarReserva(String roomName, double diariaPrice) {
        initComponents(roomName, diariaPrice);
    }

    private void initComponents(String roomName, double diariaPrice) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Confirmar Reserva");
        setPreferredSize(new Dimension(400, 300));

        titleLabel = new JLabel("Reserva do " + roomName);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);

        quantidadeDiariasLabel = new JLabel("Quantidade de Diárias");
        quantidadeDiariasField = new JTextField(5);

        precoDiariaLabel = new JLabel("Preço Diária");
        precoDiariaValueLabel = new JLabel("$" + diariaPrice);

        precoTotalLabel = new JLabel("Preço Total");
        precoTotalValueLabel = new JLabel();

        confirmarPagamentoButton = new JButton("Confirmar Pagamento");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        // Definindo o gap entre os elementos e as bordas
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
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
                )
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
        );

        confirmarPagamentoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new Pagamento().setVisible(true);
            }
        });

        pack();
        setLocationRelativeTo(null); // Isso centraliza a janela no meio da tela
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new ConfirmarReserva("Nome do Quarto 1", 100.00).setVisible(true);
        });
    }
}
