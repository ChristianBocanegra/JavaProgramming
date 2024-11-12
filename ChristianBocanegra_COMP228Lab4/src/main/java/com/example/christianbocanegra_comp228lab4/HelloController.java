package com.example.christianbocanegra_comp228lab4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML
    private TextField nameField, addressField, cityField, provinceField, postalCodeField, phoneField, emailField;

    @FXML
    private RadioButton csRadio, businessRadio;

    @FXML
    private ComboBox<String> courseComboBox;

    @FXML
    private ListView<String> courseListView;

    @FXML
    private CheckBox studentCouncilCheck, volunteerWorkCheck;

    @FXML
    private TextArea displayArea;

    private ObservableList<String> csCourses = FXCollections.observableArrayList("Java", "Python", "C#", "JavaScript", "PHP");
    private ObservableList<String> businessCourses = FXCollections.observableArrayList("Economics", "International Business", "Digital Marketing", "Business Communication");

    @FXML
    public void initialize() {
        ToggleGroup majorGroup = new ToggleGroup();
        csRadio.setToggleGroup(majorGroup);
        businessRadio.setToggleGroup(majorGroup);

        csRadio.setOnAction(event -> courseComboBox.setItems(csCourses));
        businessRadio.setOnAction(event -> courseComboBox.setItems(businessCourses));

        courseComboBox.setOnAction(event -> addCourse());
    }

    @FXML
    protected void addCourse() {
        String selectedCourse = courseComboBox.getSelectionModel().getSelectedItem();
        if (selectedCourse != null && !courseListView.getItems().contains(selectedCourse)) {
            courseListView.getItems().add(selectedCourse);
        }
    }

    @FXML
    protected void displayInfo() {
        StringBuilder studentInfo = new StringBuilder();
        studentInfo.append(nameField.getText()).append(", ")
                .append(addressField.getText()).append(", ")
                .append(cityField.getText()).append(", ")
                .append(provinceField.getText()).append(", ")
                .append(postalCodeField.getText()).append(", ")
                .append(phoneField.getText()).append(", ")
                .append(emailField.getText()).append("\n");

        studentInfo.append("Courses:\n");

        for (String course : courseListView.getItems()) {
            studentInfo.append(course).append("\n");
        }

        if (csRadio.isSelected()) {
            studentInfo.append("Major: Computer Science\n");
        } else if (businessRadio.isSelected()) {
            studentInfo.append("Major: Business\n");
        }

        if (studentCouncilCheck.isSelected()) {
            studentInfo.append("Involvement: Student Council\n");
        }
        if (volunteerWorkCheck.isSelected()) {
            studentInfo.append("Involvement: Volunteer Work\n");
        }

        displayArea.setText(studentInfo.toString());


    }
}
