import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinhasReservas extends JFrame {
    private JLabel title;
    private JLabel subTitle;
    private JPanel reservaPanel;
    private JButton quartosButton;
    private JButton backButton;

    public MinhasReservas() {
        initComponents();
    }

    private void initComponents() {
        // Configurações da janela
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Minhas Reservas");
        setPreferredSize(new Dimension(800, 600));

        // Define a cor de fundo
        getContentPane().setBackground(Color.decode("#ffffff"));

        // Título da aplicação
        title = new JLabel("JavaBNB");
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(Color.decode("#000000"));

        // Subtítulo
        subTitle = new JLabel("Minhas Reservas");
        subTitle.setFont(new Font("Serif", Font.BOLD, 18));
        subTitle.setHorizontalAlignment(SwingConstants.CENTER);

        // Painel de reservas
        reservaPanel = new JPanel();
        reservaPanel.setLayout(new GridLayout(0, 1));

        // Simulação de reservas - você deve preencher com seus próprios dados
        createReservaPanel("Nome do Quarto 1", "Data de Entrada 1", "Data de Saída 1", "Número de Hóspedes 1");
        createReservaPanel("Nome do Quarto 2", "Data de Entrada 2", "Data de Saída 2", "Número de Hóspedes 2");

        // Botão "Quartos"
        quartosButton = new JButton("Quartos");

        quartosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                abrirTelaQuartos();
            }
        });

        backButton = new JButton("Tela Inicial");

        // Define a ação do botão "Voltar"
        backButton.addActionListener(e -> {
            TelaInicial telaInicial = new TelaInicial();
            telaInicial.setVisible(true); 
            dispose(); // Fecha a tela de Minhas Reservas
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
        setLocationRelativeTo(null); // Isso centraliza a janela no meio da tela
    }

    private void createReservaPanel(String roomName, String checkIn, String checkOut, String guests) {
        JPanel reservaItem = new JPanel();
        reservaItem.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        reservaItem.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel roomLabel = new JLabel("Quarto: " + roomName);
        JLabel checkInLabel = new JLabel("Check-in: " + checkIn);
        JLabel checkOutLabel = new JLabel("Check-out: " + checkOut);
        JLabel guestsLabel = new JLabel("Hóspedes: " + guests);

        JButton deleteButton = new JButton("Excluir");

        reservaItem.add(roomLabel);
        reservaItem.add(checkInLabel);
        reservaItem.add(checkOutLabel);
        reservaItem.add(guestsLabel);
        reservaItem.add(deleteButton);

        reservaPanel.add(reservaItem);

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Implemente a ação de exclusão aqui
                reservaPanel.remove(reservaItem);
                revalidate();
                repaint();
            }
        });
    }

    private void abrirTelaQuartos() {
        new QuartosDisponiveis().setVisible(true);
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
            java.util.logging.Logger.getLogger(MinhasReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MinhasReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MinhasReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MinhasReservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Cria uma instância da classe MinhasReservas e a torna visível
        java.awt.EventQueue.invokeLater(() -> {
            new MinhasReservas().setVisible(true);
        });
    }
}
