import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import util.Utils;

import java.io.File;

public class MainGUI extends Application {

    private Stage window;

    private MainProgram mainProgram;

    private GridPane layout;
    private Button chooseFileDirBtn;
    private Button newFileDirBtn;
    private Button convertBtn;
    private TextField pixelsAmountField;
    private TextField newFileNameField;
    private Label filePathLabel;
    private Label newPathLabel;

    private Font labelFont;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
        this.window = window;
        labelFont = Font.font("Arial", FontWeight.BOLD, 25);

        layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(20);
        layout.setHgap(20);

        addLeftSideTexts();
        addPathLabels();
        addTextFields();
        addButtons();

        setOnClicks();

        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.sizeToScene();
        window.setScene(scene);
//        window.setResizable(false);
        window.show();
    }

    private void addLeftSideTexts() {
        Label label1 = new Label("Image path:");
        label1.setFont(labelFont);
        label1.setMinWidth(200);
        label1.setMaxWidth(200);
        layout.add(label1, 0, 0);

        Label label2 = new Label("New image path:");
        label2.setFont(labelFont);
        label2.setMinWidth(200);
        label2.setMaxWidth(200);
        layout.add(label2, 0, 1);

        Label label3 = new Label("Colors:");
        label3.setFont(labelFont);
        label3.setMinWidth(200);
        label3.setMaxWidth(200);
        layout.add(label3, 0, 2);
    }

    private void addPathLabels() {
        filePathLabel = new Label("Choose image");
        filePathLabel.setFont(labelFont);
        filePathLabel.setWrapText(true);
        filePathLabel.setMaxWidth(500);
        filePathLabel.textProperty().addListener((observable, oldValue, newValue) -> {
            window.sizeToScene();
            window.centerOnScreen();
        });
        layout.add(filePathLabel, 1, 0);

        newPathLabel = new Label("C:/");
        newPathLabel.setFont(labelFont);
        newPathLabel.setWrapText(true);
        newPathLabel.setMaxWidth(500);
        newPathLabel.textProperty().addListener((observable, oldValue, newValue) -> {
            window.sizeToScene();
            window.centerOnScreen();
        });
        layout.add(newPathLabel, 1, 1);
    }

    private void addTextFields() {
        pixelsAmountField = new TextField("16");
        pixelsAmountField.setFont(labelFont);
        pixelsAmountField.setMaxWidth(150);
        layout.add(pixelsAmountField, 1, 2);

        newFileNameField = new TextField("new.jpg");
        newFileNameField.setFont(labelFont);
        newFileNameField.setMaxWidth(200);
        layout.add(newFileNameField, 2, 1);
    }

    private void addButtons() {
        chooseFileDirBtn = new Button("Choose");
        chooseFileDirBtn.setFont(labelFont);
        layout.add(chooseFileDirBtn, 3, 0);

        newFileDirBtn = new Button("Choose");
        newFileDirBtn.setFont(labelFont);
        layout.add(newFileDirBtn, 3, 1);

        convertBtn = new Button("Convert");
        convertBtn.setFont(labelFont);
        convertBtn.setMinWidth(345);
        GridPane.setHalignment(convertBtn, HPos.CENTER);
        layout.add(convertBtn, 2, 2, 2, 1);
    }

    private void setOnClicks() {
        chooseFileDirBtn.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png", "*.bmp", "*.gif"));
            File image = fileChooser.showOpenDialog(window);
            if (image != null)
                filePathLabel.setText(image.getAbsolutePath());
        });

        newFileDirBtn.setOnAction(event -> {
            DirectoryChooser fileChooser = new DirectoryChooser();
            File directory = fileChooser.showDialog(window);
            if (directory != null)
                newPathLabel.setText(directory.getAbsolutePath() + "\\");
        });

        convertBtn.setOnAction(event -> {
            String imagePath = filePathLabel.getText();

            String groupCount = pixelsAmountField.getText();
            if (!parseGroupCount(groupCount)) {
                Utils.showAlertDialog("Wrong group count!");
                return;
            }

            mainProgram = new MainProgram(imagePath, Integer.parseInt(groupCount));
            mainProgram.run();

            String newFile = newPathLabel.getText() + newFileNameField.getText();
            System.out.println(newFile);
            mainProgram.saveNewImage(newFile);
        });
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
