import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RenklendirmeEkrani extends JFrame {
    private Color[][] cellColors;
    private JButton btnViewFabric, btnSelectColor;

    public RenklendirmeEkrani(Color[][] cellColors) {
        this.cellColors = cellColors;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Atký-Çözgü Renk Planý");
        setSize(900, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        setupMatrixPanel();
        setupControlPanel();
    }

    private void setupMatrixPanel() {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(156, 41, 464, 209);
        getContentPane().add(scrollPane);

        JPanel matrixPanel = new JPanel(new GridLayout(cellColors.length, cellColors[0].length));
        for (int i = 0; i < cellColors.length; i++) {
            for (int j = 0; j < cellColors[i].length; j++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell.setBackground(cellColors[i][j]);  // Set the background color based on the array
                matrixPanel.add(cell);
            }
        }
        scrollPane.setViewportView(matrixPanel);
    }

    private void setupControlPanel() {
        btnViewFabric = new JButton("Kumaþ Görüntüsü");
        btnViewFabric.setBounds(630, 69, 113, 21);
        getContentPane().add(btnViewFabric);

        btnSelectColor = new JButton("Renk Seçiniz");
        btnSelectColor.setBounds(156, 317, 464, 21);
        btnSelectColor.addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(this, "Renk Seç", null);
            if (newColor != null) {
                // Implement how to handle the color selection
            }
        });
        getContentPane().add(btnSelectColor);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Color[][] colors = new Color[10][10]; // Simulate receiving a colored matrix
            for (int i = 0; i < colors.length; i++) {
                for (int j = 0; j < colors[i].length; j++) {
                    colors[i][j] = (i + j) % 2 == 0 ? Color.RED : Color.WHITE;  // Example color pattern
                }
            }
            RenklendirmeEkrani frame = new RenklendirmeEkrani(colors);
            frame.setVisible(true);
        });
    }
}
