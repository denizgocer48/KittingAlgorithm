import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class TaharArmür {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JPanel panel;
    private JPanel panel_1;
    private JPanel panel_2;
    private JButton btnNewButton_1;
    private Color[][] cellColors; // Store color information for each cell

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TaharArmür window = new TaharArmür();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TaharArmür() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 721, 430);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Satýr: ");
        lblNewLabel.setBounds(10, 10, 45, 13);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Sütun: ");
        lblNewLabel_1.setBounds(10, 33, 45, 13);
        frame.getContentPane().add(lblNewLabel_1);

        textField = new JTextField();
        textField.setBounds(49, 7, 96, 19);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(49, 30, 96, 19);
        frame.getContentPane().add(textField_1);
        textField_1.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(95, 73, 168, 178);
        frame.getContentPane().add(scrollPane);

        panel = new JPanel();
        scrollPane.setViewportView(panel);

        JLabel lblNewLabel_2 = new JLabel("Örgü Planý : ");
        lblNewLabel_2.setBounds(96, 59, 75, 13);
        frame.getContentPane().add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Armür Planý: ");
        lblNewLabel_3.setBounds(514, 59, 96, 13);
        frame.getContentPane().add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("Tahar Planý: ");
        lblNewLabel_4.setBounds(306, 59, 80, 13);
        frame.getContentPane().add(lblNewLabel_4);

        JButton btnNewButton = new JButton("Oluþtur");
        btnNewButton.setBounds(0, 96, 85, 21);
        frame.getContentPane().add(btnNewButton);

        btnNewButton.addActionListener(e -> {
            int row = Integer.parseInt(textField.getText());
            int col = Integer.parseInt(textField_1.getText());
            createMatrix(row, col);
        });

        JLabel lblNewLabel_5 = new JLabel("Çözgü Ýpliði");
        lblNewLabel_5.setBounds(105, 252, 87, 13);
        frame.getContentPane().add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Atký Ýpliði");
        lblNewLabel_6.setBounds(35, 195, 61, 13);
        frame.getContentPane().add(lblNewLabel_6);

        btnNewButton_1 = new JButton("Örgü Sil");
        btnNewButton_1.setBounds(0, 127, 85, 21);
        frame.getContentPane().add(btnNewButton_1);

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetMatrix();
            }
        });

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(306, 73, 168, 178);
        frame.getContentPane().add(scrollPane_1);
        
        panel_1 = new JPanel();
        scrollPane_1.setViewportView(panel_1);

        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setBounds(510, 73, 168, 178);
        frame.getContentPane().add(scrollPane_2);

        panel_2 = new JPanel();
        scrollPane_2.setViewportView(panel_2);
        
        JButton btnNewButton_2 = new JButton("Tahar Oluþtur");
        btnNewButton_2.setBounds(306, 258, 117, 21);
        frame.getContentPane().add(btnNewButton_2);
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createTahar();
            }
        });
        
        
        JButton btnNewButton_3 = new JButton("Arm\u00FCr Olu\u015Ftur");
        btnNewButton_3.setBounds(510, 258, 117, 21);
        frame.getContentPane().add(btnNewButton_3);               
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createArmur();
            }
        });
        JButton btnNewButton_4 = new JButton("Boyama");
        btnNewButton_4.setBounds(0, 158, 85, 21);
        frame.getContentPane().add(btnNewButton_4);
        btnNewButton_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRenklendirmeEkraný(); // Corrected action: call openRenklendirmeEkraný() method
            }
        });
    }
    private void openRenklendirmeEkraný() {
        // TaharArmür sýnýfýndan renk matrisini al
        Color[][] colors = getCellColors();
        RenklendirmeEkrani renklendirmeEkraný = new RenklendirmeEkrani(colors);
        renklendirmeEkraný.setVisible(true);
    }
    private void createMatrix(int row, int col) {
        // Clear existing panels and their components
        panel.removeAll();
        panel.setLayout(new GridLayout(row + 1, col + 1)); // +1 for row and column numbers

        // Ensure cellColors is initialized correctly
        if (cellColors == null || cellColors.length != row || cellColors[0].length != col) {
            cellColors = new Color[row][col];
        }

        // Add empty label for the top-left corner as placeholder
        panel.add(new JLabel());

        // Add column number labels above the matrix
        for (int j = 0; j < col; j++) {
            JLabel colLabel = new JLabel(String.valueOf(j));
            colLabel.setHorizontalAlignment(JLabel.CENTER);
            colLabel.setPreferredSize(new Dimension(40, 20)); // Set a fixed size for column numbers
            panel.add(colLabel);
        }

        // Add row number labels to the left of the matrix and fill the matrix with clickable cells
        for (int i = 0; i < row; i++) {
            JLabel rowLabel = new JLabel(String.valueOf(i));
            rowLabel.setHorizontalAlignment(JLabel.CENTER);
            rowLabel.setPreferredSize(new Dimension(40, 20)); // Set a fixed size for row numbers
            panel.add(rowLabel);

            for (int j = 0; j < col; j++) {
                JPanel cell = new JPanel();
                cell.setBackground(java.awt.Color.WHITE);
                cell.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));
                int finalI = i; // Capture row index for use in the listener
                int finalJ = j; // Capture column index for use in the listener
                cell.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cell.setBackground(java.awt.Color.RED);
                        cellColors[finalI][finalJ] = java.awt.Color.RED; // Update the color state
                    }
                });
                panel.add(cell);
            }
        }

        panel.revalidate();
        panel.repaint();
    }

    private void createArmur() {
        int row = cellColors.length;
        int col = cellColors[0].length;

        // Initialize a new matrix based on the colors of the cells
        Color[][] armurColors = new Color[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                armurColors[i][j] = cellColors[i][j] != null ? cellColors[i][j] : Color.WHITE;
            }
        }

        // Create a new matrix based on the colors
        createMatrixWithColors(row, col, armurColors, panel_2);
    }
    
    private void createTahar() {
        int row = cellColors.length;
        int col = cellColors[0].length;

        // Initialize a new matrix based on the colors of the cells
        Color[][] taharColors = new Color[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                taharColors[i][j] = cellColors[i][j] != null ? cellColors[i][j] : Color.WHITE;
            }
        }

        // Create a new matrix based on the colors
        createMatrixWithColors(row, col, taharColors, panel_1);
    }
    private void createMatrixWithColors(int row, int col, Color[][] colors, JPanel targetPanel) {
        targetPanel.removeAll();
        targetPanel.setLayout(new GridLayout(row + 1, col + 1)); // +1 for row and column numbers

        // Add empty label for the top-left corner
        targetPanel.add(new JLabel());

        // Add column number labels above the matrix
        for (int j = 0; j < col; j++) {
            JLabel colLabel = new JLabel(String.valueOf(j));
            colLabel.setHorizontalAlignment(JLabel.CENTER);
            colLabel.setPreferredSize(new Dimension(40, 20)); // Set a fixed size for column numbers
            targetPanel.add(colLabel);
        }

        // Add row number labels to the left of the matrix and fill the matrix with colored cells
        for (int i = 0; i < row; i++) {
            // Add row number label
            JLabel rowLabel = new JLabel(String.valueOf(i));
            rowLabel.setHorizontalAlignment(JLabel.CENTER);
            rowLabel.setPreferredSize(new Dimension(40, 20)); // Set a fixed size for row numbers
            targetPanel.add(rowLabel);

            // Fill the matrix with colored cells
            for (int j = 0; j < col; j++) {
                JPanel cell = new JPanel();
                cell.setBackground(colors[i][j]);
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                targetPanel.add(cell);
            }
        }

        targetPanel.revalidate();
        targetPanel.repaint();
    }

    private void resetMatrix() {
        // Reset the graphical components
        Component[] components = panel.getComponents();
        if (components.length > 0 && cellColors != null) {
            int col = cellColors[0].length;
            for (Component component : components) {
                if (component instanceof JPanel) {
                    JPanel cell = (JPanel) component;
                    cell.setBackground(java.awt.Color.WHITE);
                }
            }
        }

        // Reset the color tracking array
        if (cellColors != null) {
            for (int i = 0; i < cellColors.length; i++) {
                Arrays.fill(cellColors[i], null);
            }
        }

        panel.revalidate();
        panel.repaint();
    }
    public Color[][] getCellColors() {
        return cellColors;
    }
}
