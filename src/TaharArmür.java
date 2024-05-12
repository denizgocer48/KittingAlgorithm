import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class TaharArmür {

    private JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JPanel panel;
    private JPanel panel_1;
    private JPanel panel_2;
    private JButton btnNewButton_1;
    private Color[][] cellColors; // Store color information for each cell
    private Color[][] taharPlan;
    
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
            	createTaharPlanFromMatrix(getCellColors());
            }
        });
        
        
        JButton btnNewButton_3 = new JButton("Arm\u00FCr Olu\u015Ftur");
        btnNewButton_3.setBounds(510, 258, 117, 21);
        frame.getContentPane().add(btnNewButton_3);               
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	createArmurPlanFromTahar();
            }
        });
        JButton btnNewButton_4 = new JButton("Boyama");
        btnNewButton_4.setBounds(0, 158, 85, 21);
        frame.getContentPane().add(btnNewButton_4);
        
        JButton btnNewButton_5 = new JButton("Tasar\u0131m Form\u00FClleri");
        btnNewButton_5.setBounds(35, 348, 183, 21);
        frame.getContentPane().add(btnNewButton_5);
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openTasarimFormulleriPage();
            }
        });
        
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
    private static void openTasarimFormulleriPage() {
        JFrame tasarimFormulleriFrame = new JFrame("Tasarým Formülleri");
        tasarimFormulleriFrame.setVisible(true);
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

    private void createArmurPlanFromTahar() {
        // Assuming taharPlan and orguPlan are already defined
        int rows = taharPlan.length;
        int cols = taharPlan[0].length;       

        HashMap<String, Integer> uniquePatterns = new HashMap<>();
        ArrayList<Integer> columnsToCopy = new ArrayList<>();

        // Identify unique patterns and the first column index for each pattern
        for (int col = 0; col < cols; col++) {
            StringBuilder pattern = new StringBuilder();
            for (int row = 0; row < rows; row++) {
                pattern.append(taharPlan[row][col].equals(Color.RED) ? "1" : "0");
            }
            String patStr = pattern.toString();
            if (!uniquePatterns.containsKey(patStr)) {
                uniquePatterns.put(patStr, col);
                columnsToCopy.add(col);
            }
        }
        
        // Create Armür Plan based on the identified columns
        Color[][] armurPlan = new Color[rows][columnsToCopy.size()];
        int targetCol = 0;
        for (Integer col : columnsToCopy) {
            for (int row = 0; row < rows; row++) {
                armurPlan[row][targetCol] = cellColors[row][col];
            }
            targetCol++;
        }

        // Display the Armür Plan
        displayArmurPlan(armurPlan);
    }

    private void displayArmurPlan(Color[][] armurPlan) {
        int rows = armurPlan.length;
        int cols = armurPlan[0].length;
        int cellSize = 30;  // Define the preferred size for each cell

        // Create the main panel with grid layout to hold the headers and cells
        JPanel matrixPanel = new JPanel(new GridLayout(rows + 1, cols + 1));  // +1 for the header row and column
        matrixPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));  // Adds a border around the grid for clarity

        // First cell in the top-left corner (empty for headers intersection)
        matrixPanel.add(new JLabel(""));
        
        // Adding column headers
        for (int j = 0; j < cols; j++) {
            JLabel header = new JLabel(Integer.toString(j), SwingConstants.CENTER);
            header.setPreferredSize(new Dimension(cellSize, cellSize));
            matrixPanel.add(header);
        }

        // Adding row headers and cells
        for (int i = 0; i < rows; i++) {
            // Row header at the start of each row
            JLabel rowHeader = new JLabel(Integer.toString(i), SwingConstants.CENTER);
            rowHeader.setPreferredSize(new Dimension(cellSize, cellSize));
            matrixPanel.add(rowHeader);

            for (int j = 0; j < cols; j++) {
                JPanel cell = new JPanel();
                cell.setPreferredSize(new Dimension(cellSize, cellSize));
                cell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cell.setBackground(Color.WHITE); // Initially set all cells to white
                matrixPanel.add(cell);
            }
        }

        // Apply colors from the Armür Plan data
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                JPanel cellPanel = (JPanel) matrixPanel.getComponent((i + 1) * (cols + 1) + j + 1);
                cellPanel.setBackground(armurPlan[i][j]);
            }
        }

        // Ensure the JScrollPane contains only this matrixPanel
        JScrollPane scrollPane = new JScrollPane(matrixPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Assuming panel_2 is correctly configured
        panel_2.removeAll();
        panel_2.setLayout(new BorderLayout());  // Use BorderLayout for proper placement
        panel_2.add(scrollPane, BorderLayout.CENTER);

        panel_2.revalidate();
        panel_2.repaint();
    }

    
    private void createTaharPlanFromMatrix(Color[][] inputMatrix) {
        int rows = inputMatrix.length;
        int cols = inputMatrix[0].length;

        taharPlan = new Color[rows][cols];
        // Initialize the Tahar Plan as unmarked (white)
        for (Color[] row : taharPlan) {
            Arrays.fill(row, Color.WHITE);
        }

        // Begin marking from (0,0) regardless of the input matrix's initial state
        taharPlan[0][0] = Color.RED;

        int[] rowForColumn = new int[cols];  // Array to store the row index used for each column's marking
        rowForColumn[0] = 0;  // First column is marked at row 0
        int maxRowMarked = 0;  // Track the highest row that has been marked

        // Process each column, beginning from the second one (since the first is already marked)
        for (int col = 1; col < cols; col++) {
            boolean foundIdentical = false;
            // Check all previous columns for an identical match
            for (int prevCol = 0; prevCol < col; prevCol++) {
                if (areColumnsIdentical(inputMatrix, col, prevCol)) {
                    // If an identical column is found, use the same row for marking as the matched column
                    int matchedRow = rowForColumn[prevCol];
                    taharPlan[matchedRow][col] = Color.RED;
                    rowForColumn[col] = matchedRow;  // Record the row used for this column
                    foundIdentical = true;
                    break;
                }
            }

            if (!foundIdentical) {
                // If no identical column is found, move to the next row from the max marked row (wrap around if necessary) and mark
                int newRow = (maxRowMarked + 1) % rows;
                taharPlan[newRow][col] = Color.RED;
                rowForColumn[col] = newRow;  // Record the new row used for this column
                maxRowMarked = Math.max(maxRowMarked, newRow);  // Update the highest marked row
            }
        }

        // Update the GUI with the new Tahar Plan
        createMatrixWithColors(rows, cols, taharPlan, panel_1);
    }

    private boolean areColumnsIdentical(Color[][] matrix, int col1, int col2) {
        int rows = matrix.length;
        for (int row = 0; row < rows; row++) {
            Color cell1 = matrix[row][col1] == null ? Color.WHITE : matrix[row][col1];
            Color cell2 = matrix[row][col2] == null ? Color.WHITE : matrix[row][col2];
            if (!cell1.equals(cell2)) {
                return false;
            }
        }
        return true;
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
