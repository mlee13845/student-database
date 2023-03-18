package com.example.tuitonfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    TuitionManager t1 = new TuitionManager();
    @FXML
    private DatePicker DOB;

    @FXML
    private TextField FName;

    @FXML
    private TextField LName;
    @FXML
    private Label test;

    @FXML
    private ChoiceBox<String> myChoiceBox;

    @FXML
    private TextField num_creds;

    private String[] majors = {"EE", "BAIT", "CS", "MATH", "ITI"};

    private String birth_date;

    private String student_major;

    private String remove_birth_date;
    @FXML
    private DatePicker RDOB;

    @FXML
    private TextField RFname;

    @FXML
    private TextField RLName;

    @FXML
    private Label display_remove;

    @FXML
    private RadioButton button_sort1;

    @FXML
    private RadioButton button_sortMajor;

    @FXML
    private RadioButton button_sortSchool;

    @FXML
    private RadioButton button_sortStanding;
    @FXML
    void button_on_click(ActionEvent event) {
        String first_name = FName.getText();
        String last_name = LName.getText();
        String cred = num_creds.getText();
        int convert_cred = t1.credits_convert(cred);

        if(convert_cred == -1000){
            test.setText("Invalid Credit Input.");
        }else if(convert_cred < 0){
            test.setText("Credits completed invalid: cannot be negative!");
        }else{
            Student newStudent = t1.create_student(first_name,last_name,birth_date,student_major,cred);
            Date currDate = new Date();
            if(!t1.old_enough(currDate, newStudent.getProfile().get_dob())){
                test.setText("DOB invalid: " + newStudent.getProfile().get_dob().toString() + " younger than 16 years old.");
            }else if(t1.add_student(newStudent) == true){
                test.setText(newStudent.toString() + " added to roster.");
            }else{
                test.setText(newStudent.toString() + " is already in the roster.");
            }
        }

    }

    @FXML
    void dob_on_choice(ActionEvent event) {
        LocalDate myDate = DOB.getValue();
        birth_date = myDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        myChoiceBox.getItems().addAll(majors);
        myChoiceBox.setOnAction(this::get_major);
    }

    public void get_major(ActionEvent event){
        student_major = myChoiceBox.getValue();
    }

    @FXML
    void date_remove_choice(ActionEvent event) {
         LocalDate myDate = RDOB.getValue();
         remove_birth_date = myDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));
    }


    @FXML
    void remove_on_action(ActionEvent event) {
        String first_name = RFname.getText();
        String last_name = RLName.getText();
        Date date = new Date(remove_birth_date);
        Profile p1 = new Profile(first_name,last_name,date);
        int student_loc = t1.roster.student_index(p1);
        Student[] temp_arr = t1.roster.getRoster();
        if(student_loc != -1) {
            t1.roster.remove(temp_arr[student_loc]);
            display_remove.setText(p1.toString() + " dropped.");
        }else{
            display_remove.setText(p1.toString() + " is not in the roster.");
        }

    }

    @FXML
    int display_choice(ActionEvent event) {
        if(button_sort1.isSelected()){
            return 1;
        }else if(button_sortSchool.isSelected()){
            return 2;
        }else if(button_sortStanding.isSelected()){
            return 3;
        }else if(button_sortMajor.isSelected()){
            return 4;
        }
        return 0;
    }
    @FXML
    void select_display(ActionEvent event) {


        int num = display_choice(event);
        if(num == 1){
            final Stage newStage = new Stage();
            Group newRoot = new Group();
            Scene scene = new Scene(newRoot, 500, 300);
            newStage.setScene(scene);
            newStage.show();
            Button btnClose = new Button("Close");
            VBox vBox = new VBox();

            Label newLabel = new Label();
            newLabel.setText(t1.roster.print2());
            btnClose.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    newStage.close();
                }
            });
            vBox.getChildren().addAll(newLabel,btnClose);
            newRoot.getChildren().add(vBox);
        }else if(num == 2){
            final Stage newStage = new Stage();
            Group newRoot = new Group();
            Scene scene = new Scene(newRoot, 500, 300);
            newStage.setScene(scene);
            newStage.show();
            Button btnClose = new Button("Close");
            VBox vBox = new VBox();

            Label newLabel = new Label();
            newLabel.setText(t1.roster.printBySchoolMajor());
            btnClose.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    newStage.close();
                }
            });
            vBox.getChildren().addAll(newLabel,btnClose);
            newRoot.getChildren().add(vBox);
        }else if(num == 3){
            final Stage newStage = new Stage();
            Group newRoot = new Group();
            Scene scene = new Scene(newRoot, 500, 300);
            newStage.setScene(scene);
            newStage.show();
            Button btnClose = new Button("Close");
            VBox vBox = new VBox();

            Label newLabel = new Label();
            newLabel.setText(t1.roster.printByStanding());
            btnClose.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {
                    newStage.close();
                }
            });
            vBox.getChildren().addAll(newLabel,btnClose);
            newRoot.getChildren().add(vBox);
        }else if(num == 4){
            //newLabel.setText(button_sortMajor.getText());
        }


   }
}
