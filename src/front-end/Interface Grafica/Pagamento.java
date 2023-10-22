import javax.swing.*;
import java.awt.*;

public class Pagamento extends JFrame {
    private JLabel pagamentoLabel;

    public Pagamento() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pagamento");
        setPreferredSize(new Dimension(400, 300));

        pagamentoLabel = new JLabel("Pagamento confirmado!");
        pagamentoLabel.setFont(new Font("Serif", Font.BOLD, 24));
        pagamentoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(pagamentoLabel, BorderLayout.CENTER);

        add(panel);

        pack();
        setLocationRelativeTo(null); // Isso centraliza a janela no meio da tela
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Pagamento().setVisible(true);
        });
    }
}
