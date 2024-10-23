package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.*;
import javax.swing.*;

public class Principal {

    private JFrame frame;
    private JTextField textField;
    private static final String GABARITO = "ascendente";
    private JPanel panel_3;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Principal window = new Principal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Principal() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 575, 555);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(213, 255, 213));
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JButton btnNewButton = new JButton("");
        btnNewButton.setForeground(new Color(213, 255, 213));
        btnNewButton.setBounds(43, 143, 71, 66);
        btnNewButton.setBackground(new Color(213, 255, 213));
        panel.add(btnNewButton);

        // Removendo as bordas do botão
        btnNewButton.setBorderPainted(false);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setFocusPainted(false);
        btnNewButton.setOpaque(false);

        ImageIcon imageIcon4 = new ImageIcon(Principal.class.getResource("/img/sound.png"));
        Image image4 = imageIcon4.getImage();
        Image scaledImage4 = image4.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        imageIcon4 = new ImageIcon(scaledImage4);

        btnNewButton.setIcon(imageIcon4);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(-20, 290, 867, 364);
        lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/img/greenwave.png")));
        panel.add(lblNewLabel);

        panel_3 = new JPanel();
        panel_3.setBounds(73, 205, 414, 53);
        panel.add(panel_3);
        panel_3.setLayout(null);

        textField = new JTextField();
        textField.setBounds(0, 0, 414, 52);
        textField.setForeground(new Color(0, 128, 255));
        textField.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        panel_3.add(textField);
        textField.setColumns(10);

        // Botão de verificar resposta
        JButton btnCheck = new JButton("CONFIRMA");
        btnCheck.setBounds(237, 352, 125, 30);
        btnCheck.setBackground(new Color(60, 179, 113));
        btnCheck.setForeground(new Color(157, 255, 157));
        btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 14));
        panel.add(btnCheck);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 721, 20);
        panel_1.setBackground(new Color(157, 255, 157));
        panel.add(panel_1);
        panel_1.setLayout(null);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(541, 10, 20, 508);
        panel_2.setBackground(new Color(157, 255, 157));
        panel.add(panel_2);
        panel_2.setLayout(null);

        JPanel panel_2_1 = new JPanel();
        panel_2_1.setBounds(0, 0, 20, 508);
        panel_2_1.setBackground(new Color(157, 255, 157));
        panel.add(panel_2_1);
        panel_2_1.setLayout(null);

        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBounds(-48, 498, 721, 20);
        panel_1_1.setBackground(new Color(157, 255, 157));
        panel.add(panel_1_1);
        panel_1_1.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setBounds(39, -2, 420, 160);

        ImageIcon imageIcon = new ImageIcon(Principal.class.getResource("/img/SALMKIDSLOGO.png"));
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(scaledImage);

        lblNewLabel_2.setIcon(imageIcon);
        panel.add(lblNewLabel_2);

        ImageIcon imageIcon2 = new ImageIcon(Principal.class.getResource("/img/sound.png"));
        Image image2 = imageIcon2.getImage();
        Image scaledImage2 = image2.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
        imageIcon2 = new ImageIcon(scaledImage2);

        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setBounds(-20, 137, 459, 86);
        ImageIcon imageIcon3 = new ImageIcon(Principal.class.getResource("/img/ESCREVAOQOUVE.png"));
        Image image3 = imageIcon3.getImage();
        Image scaledImage3 = image3.getScaledInstance(500, 500, java.awt.Image.SCALE_SMOOTH);
        imageIcon3 = new ImageIcon(scaledImage3);

        lblNewLabel_4.setIcon(imageIcon3);
        panel.add(lblNewLabel_4);

        // Ação do botão Check
        btnCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean correta = atualizarResultado(textField.getText(), GABARITO);
                if (correta) {
                    WavPlayer.Playando("/audio/acertou.wav");
                } else {
                    WavPlayer.Playando("/audio/tente na proxima.wav"); // Adicione o arquivo de áudio correspondente ao erro
                }
            }
        });

        // Ação do botão de som
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WavPlayer.Playando("/audio/ascendente.wav");
            }
        });
    }

    // Função que coloca cada letra de uma palavra em um vetor de caracteres
    public static char[] palavraParaVetor(String palavra) {
        return palavra.toCharArray();
    }

    // Função que compara duas palavras e atualiza o painel com a matriz de resultados
    public boolean atualizarResultado(String tentativa, String gabarito) {
        // Transformar as palavras em vetores de caracteres
        char[] vetorTentativa = palavraParaVetor(tentativa);
        char[] vetorGabarito = palavraParaVetor(gabarito);

        // Verificar se os tamanhos das palavras são iguais
        if (vetorTentativa.length != vetorGabarito.length) {
            JOptionPane.showMessageDialog(frame, "Tamanho da palavra incorreto!", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Construir o painel de resultados
        panel_3.removeAll();
        panel_3.setLayout(new GridLayout(1, vetorTentativa.length));

        // Mapeamento de letras do gabarito
        Map<Character, Integer> mapaGabarito = new HashMap<>();
        for (char c : vetorGabarito) {
            mapaGabarito.put(c, mapaGabarito.getOrDefault(c, 0) + 1);
        }

        // Matriz para armazenar os resultados das letras
        Color[][] matrizResultados = new Color[1][vetorTentativa.length];
        boolean correta = true; // Assume que está correta até encontrar uma letra errada

        // Primeira passagem para identificar letras na posição correta
        for (int i = 0; i < vetorTentativa.length; i++) {
            if (vetorTentativa[i] == vetorGabarito[i]) {
                matrizResultados[0][i] = Color.GREEN;
                mapaGabarito.put(vetorGabarito[i], mapaGabarito.get(vetorGabarito[i]) - 1);
            } else {
                matrizResultados[0][i] = null; // Placeholder para letras que ainda serão verificadas
                correta = false; // Encontrou pelo menos uma letra errada
            }
        }

        // Segunda passagem para identificar letras corretas mas fora de posição
        for (int i = 0; i < vetorTentativa.length; i++) {
            if (matrizResultados[0][i] == null) {
                if (mapaGabarito.containsKey(vetorTentativa[i]) && mapaGabarito.get(vetorTentativa[i]) > 0) {
                    matrizResultados[0][i] = Color.YELLOW;
                    mapaGabarito.put(vetorTentativa[i], mapaGabarito.get(vetorTentativa[i]) - 1);
                } else {
                    matrizResultados[0][i] = Color.RED;
                }
            }
        }

        // Adicionar os componentes ao painel com base na matriz de resultados
        for (int i = 0; i < vetorTentativa.length; i++) {
            JLabel letraLabel = new JLabel(String.valueOf(vetorTentativa[i]), SwingConstants.CENTER);
            letraLabel.setOpaque(true);
            letraLabel.setBackground(matrizResultados[0][i]);
            letraLabel.setFont(new Font("Arial", Font.BOLD, 30));
            letraLabel.setPreferredSize(new Dimension(50, 50));
            panel_3.add(letraLabel);
        }

        // Atualizar o painel
        panel_3.revalidate();
        panel_3.repaint();

        return correta;
    }
}

// Classe para tocar áudio
class WavPlayer {
    public static synchronized void Playando(String arquivoWav) {
        try {
            InputStream is = WavPlayer.class.getResourceAsStream(arquivoWav);
            if (is == null) {
                System.err.println("Arquivo de áudio não encontrado: " + arquivoWav);
                return;
            }
            InputStream bufferedIn = new BufferedInputStream(is);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(bufferedIn);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
