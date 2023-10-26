import javax.swing.*;
import java.awt.*;

public class TelaInicial {
    public static void main(String[] args) {
        // Cria uma janela
        JFrame janela = new JFrame("Tela inicial");
        janela.setSize(500, 500);
        
        // Define a cor de fundo da janela como branco
        janela.getContentPane().setBackground(Color.decode("#ffffff"));

        janela.setLocationRelativeTo(null);

        // Cria um painel principal
        JPanel painel = new JPanel();
        // Define a cor de fundo do painel principal como branco
        painel.setBackground(Color.decode("#ffffff"));
        // Define o layout do painel como uma grade de 3 linhas e 1 coluna
        painel.setLayout(new GridLayout(3, 1));

        // Cria um painel para centralizar o texto
        JPanel textoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Define a cor de fundo do painel de texto como branco
        textoPanel.setBackground(Color.decode("#ffffff"));
        // Cria um rótulo com o texto "JavaBNB"
        JLabel label = new JLabel("JavaBNB");
        // Adiciona um recuo (espaço) entre o JLabel e os botões
        textoPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 0, 0));

        // Define uma fonte com tamanho personalizado para o rótulo
        Font fonte = new Font("Serif", Font.BOLD, 24);
        label.setForeground(Color.decode("#000000"));
        label.setFont(fonte);
        textoPanel.add(label);

        // Cria dois botões: "Login" e "Cadastro"
        JButton botao1 = new JButton("Login");
        JButton botao2 = new JButton("Cadastro");

        // Define o tamanho preferencial dos botões 
        botao1.setPreferredSize(new Dimension(150, 40));
        botao2.setPreferredSize(new Dimension(150, 40));

        // Define as cores dos textos e dos botões
        botao1.setForeground(Color.decode("#000"));
        botao2.setForeground(Color.decode("#000"));
        botao1.setBackground(Color.decode("#ffffff"));
        botao2.setBackground(Color.decode("#ffffff"));

        // Define a fonte, tamanho e peso da fonte para os textos dos botões
        Font fonteBotao = new Font("Arial", Font.BOLD, 16);
        botao1.setFont(fonteBotao);
        botao2.setFont(fonteBotao);

        // Cria um painel para os botões e centraliza-os
        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Define a cor de fundo do painel de botões como branco
        botoesPanel.setBackground(Color.decode("#ffffff"));
        botoesPanel.add(botao1);
        botoesPanel.add(botao2);

        // Adiciona os painéis ao painel principal
        painel.add(textoPanel);
        painel.add(botoesPanel);

        // Adiciona o painel principal à janela
        janela.add(painel);

        // Define o comportamento de fechamento da janela
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Torna a janela visível
        janela.setVisible(true);

        // Cria objetos para as telas de login e cadastro
        TelaLogin telaLogin = new TelaLogin();
        TelaCadastro telaCadastro = new TelaCadastro();

        // Define a ação do botão "Login"
        botao1.addActionListener(e -> {
            // Tela de login é exibida
            telaLogin.setVisible(true);
            // Esconde a tela inicial
            janela.setVisible(false);
        });
        
        // Define a ação do botão "Cadastro"
        botao2.addActionListener(e -> {
            // Tela de cadastro é exibida
            telaCadastro.setVisible(true);
            // Esconde a tela inicial
            janela.setVisible(false);
        });
    }
}
