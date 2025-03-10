package edu.westga.comp4420.junit_sample.view.codebehind;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import java.util.ArrayList;
import java.util.Collections;

import java.io.IOException;

import edu.westga.comp4420.junit_sample.Main;
import edu.westga.comp4420.junit_sample.model.Course;


/**
 * CodeBehind To Handle Processing for the HomeWindow
 *
 * @author	COMP4420
 * @version Spring 2025
 */
public class HomeWindow {   
	@FXML
    private Button addCourseButton;
	
	@FXML
    private Button updateCourseButton;
	
	@FXML
    private Button showAllCoursesButton;
	
	@FXML
	private ListView<String> allCoursesListView;
	
	@FXML
	private ListView<String> semesterListView;
	
	@FXML
	private ListView<String> departmentListView;
	
    @FXML
    void initialize() {
        assert this.addCourseButton != null : "fx:id = \"addCourseButton\" was not injected: check your FXML file 'HomeWindow.fxml'.";
		assert this.updateCourseButton != null : "fx:id = \"updateCourseButton\" was not injected: check your FXML file 'HomeWindow.fxml'.";
		assert this.showAllCoursesButton != null : "fx:id = \"showAllCoursesButton\" was not injected: check your FXML file 'HomeWindow.fxml'.";
		
		this.addCourseButton.setOnAction(event-> this.openCourseInfoWindow("Add Course"));
		this.updateCourseButton.setOnAction(event-> this.openCourseInfoWindow("Update Course"));
		this.departmentListView.setOnMouseClicked(event -> this.filterCoursesByPrefix());
		this.semesterListView.setOnMouseClicked(event -> this.filterCoursesBySemester());
		this.showAllCoursesButton.setOnAction(event-> this.refreshCourseList());
	}
		
	
	/**
     * Opens the course info window for adding or updating a course.
     * 
     * @param action The action to be performed ("Add Course" or "Update Course").
     */	

	private void openCourseInfoWindow(String action) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(Main.COURSE_INFO_RESOURCE));
			loader.load();
			Parent parent = loader.getRoot();
			Scene scene = new Scene(parent);
			Stage courseInfoStage = new Stage();
			courseInfoStage.setTitle(action);
			courseInfoStage.setScene(scene);
			courseInfoStage.initModality(Modality.APPLICATION_MODAL);	
			CourseInfoWindow courseInfoController = loader.getController();
			courseInfoController.setHomeWindow(this);
			if (action.equals("Update Course")) {
				String selectedCourse = this.allCoursesListView.getSelectionModel().getSelectedItem();
				if (selectedCourse != null) {
					Course course = Course.findCourseByName(selectedCourse);
					courseInfoController.populateFields(course);
				} else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setContentText("Please select a course to update.");
					alert.showAndWait();
					return;
				}
			}
			courseInfoStage.showAndWait();
		} catch (IOException e) {
			Alert alertWindow = new Alert(Alert.AlertType.ERROR);
			alertWindow.setContentText("Unable to launch Course info Window");
			alertWindow.showAndWait();
		}		
    }
	
	
	/**
     * Updates the course lists when a new course is added or updated.
     * 
     * @param course The course to add to the list views.
     */
	public void updateCourseLists(Course course) {
        this.allCoursesListView.getItems().add(course.toString());  
		
		
        ArrayList<String> uniqueSemesters = new ArrayList<>(this.semesterListView.getItems());
        if (!uniqueSemesters.contains(course.getSemester())) {
            uniqueSemesters.add(course.getSemester());
        }
        
        uniqueSemesters.sort(Collections.reverseOrder());
        this.semesterListView.getItems().setAll(uniqueSemesters);
        
		
		ArrayList<String> uniqueDepartments = new ArrayList<>(this.departmentListView.getItems());
		
		if (!uniqueDepartments.contains(course.getPrefix())) {
			uniqueDepartments.add(course.getPrefix());
		}
		this.departmentListView.getItems().setAll(uniqueDepartments);
    }
	
	/**
	*Filters courses by their prefixes allowing users to organize by department
	*/
	
	public void filterCoursesByPrefix() {
		String selectedPrefix = this.departmentListView.getSelectionModel().getSelectedItem();
		if (selectedPrefix != null) {
			this.allCoursesListView.getItems().clear();
			for (Course course : Course.getAllCourses()) {
				if (course.getPrefix().equals(selectedPrefix)) {
					this.allCoursesListView.getItems().add(course.toString());
				}
			}
		}
	}
	
	
	/**
	*Filters courses by their semesters allowing users to organize by semester
	*/
	public void filterCoursesBySemester() {
		String selectedSemester = this.semesterListView.getSelectionModel().getSelectedItem();
		if (selectedSemester != null) {
			this.allCoursesListView.getItems().clear();
			for (Course course : Course.getAllCourses()) {
				if (course.getSemester().equals(selectedSemester)) {
					this.allCoursesListView.getItems().add(course.toString());
				}
			}
		}
	}
	
	/**
     * Refreshes the course list to show all courses and clears selections.
     */
	public void refreshCourseList() {
		this.allCoursesListView.getItems().clear();
		this.departmentListView.getSelectionModel().clearSelection();
		this.semesterListView.getSelectionModel().clearSelection();
		for (Course course : Course.getAllCourses()) {
			this.allCoursesListView.getItems().add(course.toString());
		}
	}
}