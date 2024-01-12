import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AdamAsmacaGUI extends JFrame implements ActionListener {

    private String[] kelimeler = {"bilgisayar", 
    "programlama", "java", 
    "openai", "yapay zeka", "gpt-3",
    "algoritma", "veritabanı", "mobil", "web", "internet", "uygulama",
    "geliştirici", "framework", "öğrenme", "proje", "kodlama", "bug",
    "debug", "siber güvenlik", "işletim sistemi", "agile", "backend",
    "frontend", "veri bilimi", "cloud", "docker", "android", "ios"};
    
    private String secilenKelime;
    private char[] dogruTahminler;
    private int kalanHak;

    private JLabel kelimeLabel;
    private JLabel kalanHakLabel;
    private JTextField tahminField;
    private JButton tahminButton;
    private JButton resetButton;

    public AdamAsmacaGUI() {
        super("Adam Asmaca Oyunu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(5, 1));

        kelimeLabel = new JLabel();
        kalanHakLabel = new JLabel();
        tahminField = new JTextField();
        tahminButton = new JButton("Tahmin Et");
        resetButton = new JButton("Reset");

        tahminButton.addActionListener(this);
        resetButton.addActionListener(this);

        add(kelimeLabel);
        add(kalanHakLabel);
        add(tahminField);
        add(tahminButton);
        add(resetButton);

        yeniOyunuBaslat();
    }

    private void yeniOyunuBaslat() {
        Random random = new Random();
        secilenKelime = kelimeler[random.nextInt(kelimeler.length)];
        dogruTahminler = new char[secilenKelime.length()];
        for (int i = 0; i < dogruTahminler.length; i++) {
            dogruTahminler[i] = '_';
        }
        kalanHak = 6;

        kelimeLabel.setText("Kelime: " + new String(dogruTahminler));
        kalanHakLabel.setText("Kalan Hak: " + kalanHak);
        tahminField.setText("");
        tahminButton.setEnabled(true);
        resetButton.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tahminButton) {
            String tahmin = tahminField.getText();

            if (tahmin.length() == 1) {
                boolean dogruTahminYapildi = false;
                for (int i = 0; i < secilenKelime.length(); i++) {
                    if (secilenKelime.charAt(i) == tahmin.charAt(0)) {
                        dogruTahminler[i] = tahmin.charAt(0);
                        dogruTahminYapildi = true;
                    }
                }

                if (!dogruTahminYapildi) {
                    kalanHak--;
                }

                kelimeLabel.setText("Kelime: " + new String(dogruTahminler));
                kalanHakLabel.setText("Kalan Hak: " + kalanHak);

                if (kalanHak == 0) {
                    JOptionPane.showMessageDialog(this, "Üzgünüm, haklarınız tükendi. Doğru kelime: " + secilenKelime);
                    tahminButton.setEnabled(false);
                    resetButton.setEnabled(true);
                } else if (new String(dogruTahminler).equals(secilenKelime)) {
                    JOptionPane.showMessageDialog(this, "Tebrikler! Doğru kelimeyi buldunuz: " + secilenKelime);
                    tahminButton.setEnabled(false);
                    resetButton.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Lütfen sadece bir harf girin.");
            }
        } else if (e.getSource() == resetButton) {
            yeniOyunuBaslat();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdamAsmacaGUI().setVisible(true);
        });
    }
}
