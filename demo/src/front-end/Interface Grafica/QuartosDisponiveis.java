import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuartosDisponiveis extends JFrame {
    private JLabel title;
    private JPanel quartosPanel;
    private JButton reservasButton;

    public QuartosDisponiveis() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quartos Disponíveis");
        setPreferredSize(new Dimension(800, 600));

        getContentPane().setBackground(Color.decode("#ffffff"));

        title = new JLabel("JavaBNB");
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        quartosPanel = new JPanel();
        quartosPanel.setLayout(new BoxLayout(quartosPanel, BoxLayout.Y_AXIS));

        createQuartoPanel("Quarto 1", "Disponível", "R$100");
        createQuartoPanel("Quarto 2", "Ocupado", "R$150");
        createQuartoPanel("Quarto 3", "Disponível", "R$80");

        reservasButton = new JButton("Reservas");
        reservasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                abrirTelaReservas();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true); // Ativar o espaço automático
        layout.setAutoCreateContainerGaps(true); // Ativar o espaço entre contêineres

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(title)
                .addGap(30) // Adicionar um espaço de 30 pixels
                .addComponent(quartosPanel)
                .addGap(30) // Adicionar um espaço de 30 pixels
                .addComponent(reservasButton)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(title)
                .addGap(30) // Adicionar um espaço de 30 pixels
                .addComponent(quartosPanel)
                .addGap(30) // Adicionar um espaço de 30 pixels
                .addComponent(reservasButton)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void createQuartoPanel(String roomName, String availability, String price) {
        JPanel quartoItem = new JPanel();
        quartoItem.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel roomLabel = new JLabel("Quarto: " + roomName);
        JLabel availabilityLabel = new JLabel("Disponibilidade: " + availability);
        JLabel priceLabel = new JLabel("Preço: " + price);

        JButton reservaButton = new JButton("Reservar");
        reservaButton.setPreferredSize(new Dimension(100, 30));

        quartoItem.add(roomLabel);
        quartoItem.add(availabilityLabel);
        quartoItem.add(priceLabel);
        quartoItem.add(reservaButton);

        quartosPanel.add(quartoItem);

        reservaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ConfirmarReserva confirmarReserva = new ConfirmarReserva(roomName, Double.parseDouble(price.replace("R$", "").trim()));
                confirmarReserva.setVisible(true);
            }
        });
    }

    private void abrirTelaReservas() {
        new MinhasReservas().setVisible(true);
    }

    public static void main(String args[]) {
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

        java.awt.EventQueue.invokeLater(() -> {
            new QuartosDisponiveis().setVisible(true);
        });
    }
}
