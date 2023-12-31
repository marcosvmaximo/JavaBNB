import javax.swing.*;
import java.awt.*;

public class Pagamento extends JFrame {
    private String user;
    private JLabel pagamentoLabel;

    public Pagamento() {
        initComponents();
    }
    public Pagamento(String user) {
        this.user = user;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setTitle("Pagamento");
        setPreferredSize(new Dimension(400, 300));

        // Rótulo de pagamento confirmado
        pagamentoLabel = criarLabel("Pagamento confirmado!", 24, Color.BLACK);
        pagamentoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Painel para centralizar o rótulo
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(pagamentoLabel, BorderLayout.CENTER);

        add(panel);

        pack();
        setLocationRelativeTo(null);
        new MinhasReservas(this.user).setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Pagamento().setVisible(true);

        });
    }

    // Método para criar um rótulo com o texto, tamanho da fonte e cor especificados
    private JLabel criarLabel(String texto, int fontSize, Color cor) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Serif", Font.BOLD, fontSize));
        label.setForeground(cor);
        return label;
    }
}
