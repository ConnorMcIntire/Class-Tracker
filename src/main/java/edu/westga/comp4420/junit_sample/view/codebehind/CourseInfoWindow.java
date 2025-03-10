package edu.westga.comp4420.junit_sample.view.codebehind;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import edu.westga.comp4420.junit_sample.model.Course;


public class CourseInfoWindow {
    @FXML
    private Button saveCourseButton;
    
    @FXML
    private Button cancelButton;
    
    @FXML
    private TextField prefixField;
    
    @FXML
    private TextField numberField;
    
    @FXML
    private TextField semesterField;
    
    @FXML
    private TextField notesField;

    
    private HomeWindow homeWindow;

    
    public void setHomeWindow(HomeWindow homeWindow) {
        this.homeWindow = homeWindow;
    }

    @FXML
    void initialize() {
        assert this.saveCourseButton != null : "fx:id = \"saveCourseButton\" was not injected: check your FXML file 'CourseInfoWindow.fxml'.";
        assert this.cancelButton != null : "fx:id = \"cancelButton\" was not injected: check your FXML file 'CourseInfoWindow.fxml'.";
        assert this.prefixField != null : "fx:id = \"prefixField\" was not injected: check your FXML file 'CourseInfoWindow.fxml'.";
        assert this.numberField != null : "fx:id = \"numberField\" was not injected: check your FXML file 'CourseInfoWindow.fxml'.";
        assert this.semesterField != null : "fx:id = \"semesterField\" was not injected: check your FXML file 'CourseInfoWindow.fxml'.";
        assert this.notesField != null : "fx:id = \"notesField\" was not injected: check your FXML file 'CourseInfoWindow.fxml'.";
        
        
        this.cancelButton.setOnAction(event -> this.closeWindow()); 
    }

	/**
     * Saves the course and sees if it is a new course or update
     * 
     */
	public void saveCourse() {
		String prefix = this.prefixField.getText().trim();
		String number = this.numberField.getText().trim();
		String semester = this.semesterField.getText().trim();
		String notes = this.notesField.getText().trim();

		if (prefix.isEmpty() || number.isEmpty() || semester.isEmpty()) {
			this.showAlert(Alert.AlertType.ERROR, "Prefix, Number, and Semester are required fields");
			return;
		}

		try {
			if (this.prefixField.isDisabled()) { 
				this.updateCourse(prefix, number, semester, notes);
			} else { 
				this.addCourse(prefix, number, semester, notes);
			}
		} catch (IllegalArgumentException e) {
			this.showAlert(Alert.AlertType.ERROR, e.getMessage());
		}
	}
	
	
	/**
     * Updates the courses notes
     * 
     * @param prefix The prefix of a course
	 * @param number The number of a course
	 * @param semester The semester of a course
	 * @param notes The notes of a course
     */
	private void updateCourse(String prefix, String number, String semester, String notes) {
		Course existingCourse = Course.findCourseByName(prefix + " " + number + " " + semester);
		if (existingCourse != null) {
			existingCourse.setNotes(notes); 
			this.showAlert(Alert.AlertType.INFORMATION, "Course updated successfully!");
			if (this.homeWindow != null) {
				this.homeWindow.refreshCourseList();
			}
			this.closeWindow();
		} else {
			this.showAlert(Alert.AlertType.ERROR, "Course not found for update");
		}
	}
	
	
	/**
     * Adds a new course to the list
     * 
     * @param prefix The prefix of a course
	 * @param number The number of a course
	 * @param semester The semester of a course
	 * @param notes The notes of a course
     */
	private void addCourse(String prefix, String number, String semester, String notes) {
		Course newCourse = new Course(prefix, number, semester, notes);
		if (newCourse.addCourse()) {
			this.showAlert(Alert.AlertType.INFORMATION, "Course added successfully!");
			if (this.homeWindow != null) {
				this.homeWindow.updateCourseLists(newCourse);
			}
			this.closeWindow();
		} else {
			this.showAlert(Alert.AlertType.ERROR, "Course already exists");
		}
	}

	private void showAlert(Alert.AlertType alertType, String message) {
		Alert alert = new Alert(alertType);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	/**
     * Populates the fields when update course button is pressed
     * 
     * @param course The course being populated
     */
	public void populateFields(Course course) {
		this.prefixField.setText(course.getPrefix());
		this.numberField.setText(course.getNumber());
		this.semesterField.setText(course.getSemester());
		this.notesField.setText(course.getNotes());
		
		this.prefixField.setDisable(true);
		this.numberField.setDisable(true);
		this.semesterField.setDisable(true);
	}

    
    private void closeWindow() {
        Stage stage = (Stage) this.saveCourseButton.getScene().getWindow();
        stage.close();
    }
}