import models.Room;
import services.LodgeService;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class QuartosDisponiveis extends JFrame {
    private String user;
    private JLabel title;
    private JPanel quartosPanel;
    private JButton reservasButton;

    public QuartosDisponiveis(String user) {
        initComponents();
        this.user = user;
    }
    public QuartosDisponiveis() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quartos Disponíveis");
        setPreferredSize(new Dimension(800, 600));
        getContentPane().setBackground(Color.decode("#ffffff"));

        // Título da aplicação
        title = criarLabel("JavaBNB", 24, Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        quartosPanel = new JPanel();
        quartosPanel.setLayout(new BoxLayout(quartosPanel, BoxLayout.Y_AXIS));

        // Simulação de quartos - você pode adicionar mais quartos

        LodgeService service = new LodgeService();
        ArrayList<Room> rooms = service.getAllRooms();
        for (Room r:rooms) {
            String nomeQuarto = String.format(" %s", r.getName());
            String disponibilidade = String.format("%s", r.getIsReservation() ? "V" : "F");

            DecimalFormat df = new DecimalFormat("#.00");
            String numeroFormatado = df.format(r.getDailyPrice());
            String preco = String.format("R$%s", numeroFormatado);

            criarQuartoPanel(nomeQuarto, disponibilidade, preco);
        }
//        criarQuartoPanel("Quarto 1", "Disponível", "R$59");
//        criarQuartoPanel("Quarto 2", "Disponível", "R$119");
//        criarQuartoPanel("Quarto 3", "Disponível", "R$200");
//        criarQuartoPanel("Quarto 4", "Ocupado", "R$150");
//        criarQuartoPanel("Quarto 5", "Disponível", "R$80");

        reservasButton = criarBotao("Reservas");
        reservasButton.addActionListener(e -> abrirTelaReservas());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(title)
                .addGap(30)
                .addComponent(quartosPanel)
                .addGap(30)
                .addComponent(reservasButton)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(title)
                .addGap(30)
                .addComponent(quartosPanel)
                .addGap(30)
                .addComponent(reservasButton)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void criarQuartoPanel(String roomName, String availability, String price) {
        JPanel quartoItem = new JPanel();
        quartoItem.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel roomLabel = criarLabel("Quarto: " + roomName, 12, Color.BLACK);
        JLabel availabilityLabel = criarLabel("Disponibilidade: " + availability, 12, Color.BLACK);
        JLabel priceLabel = criarLabel("Valor Diaria: " + price, 12, Color.BLACK);

        JButton reservaButton = criarBotao("RESERVAR");
        reservaButton.setPreferredSize(new Dimension(100, 30));

        quartoItem.add(roomLabel);
        quartoItem.add(availabilityLabel);
        quartoItem.add(priceLabel);
        quartoItem.add(reservaButton);

        quartosPanel.add(quartoItem);

        reservaButton.addActionListener(e -> abrirTelaConfirmarReserva(roomName, Double
                .parseDouble(price.replace("R$", "").replace(",", ".").trim())));
    }

    private void abrirTelaReservas() {
        new MinhasReservas(this.user).setVisible(true);
        dispose();
    }

    private void abrirTelaConfirmarReserva(String roomName, double price) {
        ConfirmarReserva confirmarReserva = new ConfirmarReserva(roomName, price, this.user);
        confirmarReserva.setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        configurarLookAndFeel();

        java.awt.EventQueue.invokeLater(() -> {
            new QuartosDisponiveis().setVisible(true);
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
        return botao;
    }
}
