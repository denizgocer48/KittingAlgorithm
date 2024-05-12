import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RenklendirmeEkrani extends JFrame {
    private Color[][] cellColors;
    private Color selectedColor = Color.WHITE;
    private JPanel mainPanel;
    private JPanel rowHeaderPanel;
    private JPanel columnHeaderPanel;
    private int matrixCount = 49; // Default matrix count
    private JFrame fabricFrame;
    private final int fabricFrameSize = 600; // Fixed frame size

    public RenklendirmeEkrani(Color[][] cellColors) {
        this.cellColors = cellColors;
        getContentPane().setLayout(null);

        JScrollPane mainScrollPane = new JScrollPane();
        mainScrollPane.setBounds(116, 50, 518, 195);
        getContentPane().add(mainScrollPane);

        mainPanel = new JPanel(new GridBagLayout());
        mainScrollPane.setViewportView(mainPanel);

        JScrollPane rowHeaderScrollPane = new JScrollPane();
        rowHeaderScrollPane.setBounds(47, 50, 46, 195);
        getContentPane().add(rowHeaderScrollPane);

        rowHeaderPanel = new JPanel(new GridLayout(cellColors.length, 1));
        rowHeaderScrollPane.setViewportView(rowHeaderPanel);

        JScrollPane columnHeaderScrollPane = new JScrollPane();
        columnHeaderScrollPane.setBounds(114, 255, 520, 36);
        getContentPane().add(columnHeaderScrollPane);

        columnHeaderPanel = new JPanel(new GridLayout(1, cellColors[0].length));
        columnHeaderScrollPane.setViewportView(columnHeaderPanel);

        JButton colorButton = new JButton("Renk Seçiniz");
        colorButton.setBounds(47, 301, 122, 21);
        getContentPane().add(colorButton);
        colorButton.addActionListener(e -> {
            selectedColor = JColorChooser.showDialog(null, "Renk Seçin", selectedColor);
        });

        JButton btnNewButton = new JButton("Kumaþ Görüntüsü");
        btnNewButton.setBounds(668, 301, 146, 21);
        getContentPane().add(btnNewButton);
        btnNewButton.addActionListener(e -> createFabricView());
        initializePanels();
        initializeUI();
    }

    private void initializePanels() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = gbc.weighty = 1.0;

        // For rows, reverse the numbering
        for (int i = 0; i < cellColors.length; i++) {
            for (int j = 0; j < cellColors[i].length; j++) {
                gbc.gridx = j;
                gbc.gridy = i;
                JPanel colorPanel = createColorPanel(cellColors[i][j]);
                mainPanel.add(colorPanel, gbc);
            }
            JPanel rowColorPanel = createColorPanel(Color.WHITE);
            JLabel label = new JLabel(Integer.toString(cellColors.length - 1 - i), SwingConstants.CENTER);
            label.setPreferredSize(new Dimension(30, 30));
            rowColorPanel.add(label);
            rowHeaderPanel.add(rowColorPanel);
        }

        // For columns, retain the normal numbering
        for (int j = 0; j < cellColors[0].length; j++) {
            JPanel colColorPanel = createColorPanel(Color.WHITE);
            JLabel label = new JLabel(Integer.toString(j), SwingConstants.CENTER);
            colColorPanel.add(label);
            columnHeaderPanel.add(colColorPanel);
        }
    }

    private JPanel createColorPanel(Color initialColor) {
        JPanel colorPanel = new JPanel();
        colorPanel.setBackground(initialColor);
        colorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        colorPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                colorPanel.setBackground(selectedColor);
            }
        });
        return colorPanel;
    }

    private void createFabricView() {
        boolean[] selectedRows = new boolean[rowHeaderPanel.getComponentCount()];
        boolean[] selectedColumns = new boolean[columnHeaderPanel.getComponentCount()];

        // Identify selected rows
        for (int i = 0; i < rowHeaderPanel.getComponentCount(); i++) {
            JPanel panel = (JPanel) rowHeaderPanel.getComponent(i);
            if (panel.getBackground().equals(selectedColor)) {
                selectedRows[i] = true;
            }
        }

        // Identify selected columns
        for (int j = 0; j < columnHeaderPanel.getComponentCount(); j++) {
            JPanel panel = (JPanel) columnHeaderPanel.getComponent(j);
            if (panel.getBackground().equals(selectedColor)) {
                selectedColumns[j] = true;
            }
        }

        int matricesPerRow = 25;
        int matricesPerColumn = 25;

        int cellMatrixSize = cellColors.length; // Assuming cellColors.length is the number of rows in the color matrix

        // Adjust the fabricFrameSize
        int singleMatrixSize = ((int) Math.ceil((double) fabricFrameSize / (matricesPerRow * cellMatrixSize))) * cellMatrixSize;
        int fabricFrameSize = singleMatrixSize * matricesPerRow; // Now this is perfectly divisible

        // Create a new frame for the fabric view
        if (fabricFrame != null) {
            fabricFrame.dispose();
        }

        fabricFrame = new JFrame("Fabric View");
        fabricFrame.setUndecorated(true); // Remove window borders
        fabricFrame.setSize(fabricFrameSize, fabricFrameSize);
        fabricFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel fabricPanel = new JPanel(new GridLayout(matricesPerRow, matricesPerColumn, 0, 0)); // No gaps
        fabricFrame.add(fabricPanel);

        // Generate selected number of matrices
        for (int n = 0; n < matricesPerRow * matricesPerColumn; n++) {
            JPanel singleMatrixPanel = new JPanel(new GridLayout(cellMatrixSize, cellColors[0].length, 0, 0));
            for (int i = 0; i < cellMatrixSize; i++) {
                for (int j = 0; j < cellColors[i].length; j++) {
                    JPanel cellPanel = new JPanel();
                    cellPanel.setPreferredSize(new Dimension(singleMatrixSize / cellMatrixSize, singleMatrixSize / cellMatrixSize));
                    cellPanel.setBorder(BorderFactory.createEmptyBorder());

                    // Applying your specific color conditions
                    if ((selectedRows[i] || selectedColumns[j]) && cellColors[i][j] != null && cellColors[i][j].equals(Color.RED)) {
                        if (selectedRows[i] && selectedColumns[j]) {
                            cellPanel.setBackground(selectedColor);
                        } else if (selectedRows[i]) {
                            cellPanel.setBackground(selectedColor);
                        } else if (selectedColumns[j]) {
                            cellPanel.setBackground(selectedColor);
                        }
                    } else {
                        if (selectedRows[i]) {
                            cellPanel.setBackground(selectedColor);
                        } else {
                            cellPanel.setBackground(Color.BLACK);
                        }
                    }
                    singleMatrixPanel.add(cellPanel);
                }
            }
            fabricPanel.add(singleMatrixPanel);
        }

        fabricFrame.setLocationRelativeTo(null);
        fabricFrame.setVisible(true);
    }


    private void initializeUI() {
        setTitle("Atký-Çözgü Renk Planý");
        setSize(1000, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        if (cellColors == null || cellColors.length == 0 || cellColors[0].length == 0) {
            JOptionPane.showMessageDialog(this, "Renk matrisi boþ veya geçersiz boyutlara sahip.", "Hata", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Color[][] colors = new Color[3][3]; // Example color matrix initialization
            colors[0][0] = Color.RED;
            colors[1][1] = Color.RED;
            colors[2][2] = Color.RED;

            new RenklendirmeEkrani(colors).setVisible(true);
        });
    }
}
