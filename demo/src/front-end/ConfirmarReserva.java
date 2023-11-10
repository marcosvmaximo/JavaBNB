import models.Host;
import models.Reservation;
import models.Room;
import services.LodgeService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

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
    private String user;

    public ConfirmarReserva(String roomName, double diariaPrice, String user) {
        initComponents(roomName, diariaPrice);
        this.user = user;
    }

    private void initComponents(String roomName, double diariaPrice) {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
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
        confirmarPagamentoButton.addActionListener(e -> abrirTelaPagamento(roomName, diariaPrice, this.user));

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

    private void abrirTelaPagamento(String roomName, double diariaPrice, String user) {
        int valueDays = 0;
        try{
            String valueString = quantidadeDiariasField.getText();
            valueDays = Integer.parseInt(valueString);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(this, "Valor inserido inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        LodgeService service = new LodgeService();
        Room room = service.getRoomByName(roomName);
        Host host = service.getHostByCpf(user);

        if(room.getIsReservation()){
            JOptionPane.showMessageDialog(this, "Quarto informado já está reservado", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        LocalDate dataAtual = LocalDate.now();

        Reservation reservation = new Reservation(host, host.getId(), room, room.getId(), dataAtual, dataAtual.plusDays(valueDays), valueDays);
        boolean result = service.createReserve(reservation);

        if(result){
            new Pagamento(this.user).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao reservar esse quarto, tente novamente mais tarde.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        dispose();
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

    private void abrirTelaQuartos() {
        new QuartosDisponiveis(this.user).setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            new ConfirmarReserva("Nome do Quarto 1", 100.00, null).setVisible(true);
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
