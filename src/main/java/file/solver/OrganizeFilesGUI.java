package file.solver;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class OrganizeFilesGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    private JTextArea outputArea;
    private JButton runButton;
    private JButton selectFolderButton;
    private String selectedFolderPath = "";

    public OrganizeFilesGUI() {
        setTitle("Organize Files Based on Size");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new CardLayout());

        JPanel welcomePanel = createWelcomePanel();
        JPanel mainPanel = createMainPanel();

        add(welcomePanel, "Welcome");
        add(mainPanel, "Main");

        CardLayout layout = (CardLayout) getContentPane().getLayout();
        layout.show(getContentPane(), "Welcome");
    }

    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("<html><h2>Welcome to the File Organizer!</h2>"
                + "<p>This program sorts files by size.</p>"
                + "<p>Sizes: </p>"
                + "<ul>"
                + "<li>Large: > 100 MB</li>"
                + "<li>Medium: 10 MB - 100 MB</li>"
                + "<li>Small: &lt;= 10 MB</li>"
                + "</ul></html>", SwingConstants.CENTER);

        JButton nextButton = new JButton("Start");
        nextButton.addActionListener(e -> {
            CardLayout layout = (CardLayout) getContentPane().getLayout();
            layout.show(getContentPane(), "Main");
        });

        panel.add(welcomeLabel, BorderLayout.CENTER);
        panel.add(nextButton, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(outputArea);

        runButton = new JButton("Run");
        runButton.setBackground(new Color(60, 179, 113));
        runButton.setForeground(Color.WHITE);
        runButton.setFont(new Font("Arial", Font.BOLD, 16));
        runButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        selectFolderButton = new JButton("Select Folder");
        selectFolderButton.setBackground(new Color(30, 144, 255));
        selectFolderButton.setForeground(Color.WHITE);
        selectFolderButton.setFont(new Font("Arial", Font.BOLD, 16));
        selectFolderButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(selectFolderButton);
        buttonPanel.add(runButton);

        selectFolderButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFolder = fileChooser.getSelectedFile();
                selectedFolderPath = selectedFolder.getAbsolutePath();
                outputArea.append("Selected folder: " + selectedFolderPath + "\n");
            }
        });

        runButton.addActionListener(e -> {
            if (selectedFolderPath.isEmpty()) {
                outputArea.append("Please select a folder first!\n");
                return;
            }
            outputArea.setText("");
            OrganizeFilesBasedOnSize.printFileNameAndSize(selectedFolderPath, outputArea);
            OrganizeFilesBasedOnSize.createFolders(selectedFolderPath);
            OrganizeFilesBasedOnSize.segregateTheFilesAndMoveToFolder(selectedFolderPath, outputArea);
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrganizeFilesGUI gui = new OrganizeFilesGUI();
            gui.setVisible(true);
        });
    }
}