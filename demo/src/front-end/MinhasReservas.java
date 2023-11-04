import models.Reservation;
import services.LodgeService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MinhasReservas extends JFrame {
    private String user;
    private JLabel title;
    private JLabel subTitle;
    private JPanel reservaPanel;
    private JButton quartosButton;
    private JButton backButton;

    public MinhasReservas() {
        initComponents();
    }
    public MinhasReservas(String cpf) {
        this.user = cpf;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minhas Reservas");
        setPreferredSize(new Dimension(800, 600));
        getContentPane().setBackground(Color.decode("#ffffff"));

        title = criarLabel("JavaBNB", 24, Color.decode("#000000"));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        subTitle = criarLabel("Minhas Reservas", 18, Color.BLACK);
        subTitle.setHorizontalAlignment(SwingConstants.CENTER);

        reservaPanel = new JPanel();
        reservaPanel.setLayout(new GridLayout(0, 1));

        // Simulação de reservas - você deve preencher com seus próprios dados

        // Obter as reservas atuais
        LodgeService service = new LodgeService();
        ArrayList<Reservation> reservations = service.getAllReserveByUser(this.user);

        for (Reservation r:reservations) {
            String nomeQuarto = String.format("%s", r.getRoom().getName());
            String checkIn = String.format("%s", r.getCheckIn());
            String checkOut = String.format("%s", r.getCheckOut());
            String guestNumber = String.format("%s", r.getGuestNumbersToReserve());
            criarReservaPanel(nomeQuarto, checkIn, checkOut, guestNumber);
        }
//        criarReservaPanel("Nome do Quarto 1", "Data de Entrada 1", "Data de Saída 1", "Número de Hóspedes 1");
//        criarReservaPanel("Nome do Quarto 2", "Data de Entrada 2", "Data de Saída 2", "Número de Hóspedes 2");

        quartosButton = criarBotao("Quartos");
        quartosButton.addActionListener(e -> abrirTelaQuartos());

        backButton = criarBotao("Tela Inicial");
        backButton.addActionListener(e -> voltarParaTelaInicial());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(title)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(subTitle)
                )
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(reservaPanel)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(quartosButton)
                )
                .addGroup(layout.createSequentialGroup()
                    .addComponent(backButton)
                )
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(title)
                    .addGap(20, 20, 20)
                    .addComponent(subTitle)
                    .addGap(20, 20, 20)
                    .addComponent(reservaPanel)
                    .addGap(20, 20, 20)
                    .addComponent(quartosButton)
                    .addGap(5, 5, 5)
                    .addComponent(backButton)
                    .addGap(20, 20, 20)
                )
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void criarReservaPanel(String roomName, String checkIn, String checkOut, String guests) {
        JPanel reservaItem = new JPanel();
        reservaItem.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        reservaItem.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel roomLabel = criarLabel("Quarto: " + roomName, 12, Color.BLACK);
        JLabel checkInLabel = criarLabel("Check-in: " + checkIn, 12, Color.BLACK);
        JLabel checkOutLabel = criarLabel("Check-out: " + checkOut, 12, Color.BLACK);
        JLabel guestsLabel = criarLabel("Hóspedes: " + guests, 12, Color.BLACK);

        JButton deleteButton = criarBotao("Excluir");
        reservaItem.add(roomLabel);
        reservaItem.add(checkInLabel);
        reservaItem.add(checkOutLabel);
        reservaItem.add(guestsLabel);
        reservaItem.add(deleteButton);
        reservaPanel.add(reservaItem);

        deleteButton.addActionListener(e -> excluirReserva(reservaItem));
    }

    private void excluirReserva(JPanel reservaItem) {
        reservaPanel.remove(reservaItem);
        revalidate();
        repaint();
    }

    private void abrirTelaQuartos() {
        new QuartosDisponiveis(this.user).setVisible(true);
    }

    private void voltarParaTelaInicial() {
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        configurarLookAndFeel();

        java.awt.EventQueue.invokeLater(() -> {
            new MinhasReservas().setVisible(true);
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
