import util.Utils;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class ConverterGUI extends JFrame {

    private JPanel mainPanel;
    private JButton convertButton;
    private JButton fileChooseButton;
    private JButton newFolderChooseButton;
    private JTextField newFilename;
    private JLabel label2;
    private JLabel label1;
    private JLabel label3;
    private JLabel newFolderPath;
    private JLabel filePath;

    private JTextField groupCountTextFiled;

    private MainProgram mainProgram;

    public static void main(String[] args) {
        new ConverterGUI();
    }

    public ConverterGUI() {
        super("Convert Image Colors");
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        setOnCLicks();
    }

    private void setOnCLicks() {
        setOnFileChooseButton();
        setOnFolderChooseButton();
        setOnConvertButtonClick();
    }

    private void setOnFileChooseButton() {
        fileChooseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            String[] suffices = ImageIO.getReaderFileSuffixes();

            for (String suffice : suffices) {
                FileFilter filter = new FileNameExtensionFilter(suffice + " files", suffice);
                fileChooser.addChoosableFileFilter(filter);
            }

            int returnVal = fileChooser.showOpenDialog(ConverterGUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File chosenFile = fileChooser.getSelectedFile();
                String path = chosenFile.getAbsolutePath();
                if (checkPath(chosenFile)) {
                    filePath.setText(path);
                    ConverterGUI.this.pack();
                    ConverterGUI.this.setLocationRelativeTo(null);
                } else {
                    Utils.showAlertDialog("Only images");
                }
            }
        });
    }

    private boolean checkPath(File chosenFile) {
        String mimetype = new MimetypesFileTypeMap().getContentType(chosenFile);
        String type = mimetype.split("/")[0];
        if (type.equals("image"))
            return true;
        else
            return false;
    }

    private void setOnFolderChooseButton() {
        newFolderChooseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);

            int returnVal = fileChooser.showOpenDialog(ConverterGUI.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                newFolderPath.setText(fileChooser.getSelectedFile().toString() + "\\");
                ConverterGUI.this.pack();
                ConverterGUI.this.setLocationRelativeTo(null);
            }
        });
    }

    private void setOnConvertButtonClick() {
        convertButton.addActionListener(e -> {
            String imagePath = filePath.getText();
            if (!checkIfPathOk(imagePath)) {
                Utils.showAlertDialog("Wrong file path!");
                return;
            }

            String groupCount = groupCountTextFiled.getText();
            if (!parseGroupCount(groupCount)) {
                Utils.showAlertDialog("Wrong group count!");
                return;
            }

            mainProgram = new MainProgram(imagePath, Integer.parseInt(groupCount));
            mainProgram.run();

            String newFile = newFolderPath.getText() + newFilename.getText();
            mainProgram.saveNewImage(newFile);

            convertButton.setText("Convert");
        });
    }

    private boolean checkIfPathOk(String path) {
        File f = new File(path);
        String mimeType = new MimetypesFileTypeMap().getContentType(f);
        String type = mimeType.split("/")[0];
        if (type.equalsIgnoreCase("image"))
            return true;
        else
            return false;
    }

    private boolean parseGroupCount(String text) {
        try {
            Integer.parseInt(text);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

}