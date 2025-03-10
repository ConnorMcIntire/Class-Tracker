package edu.westga.comp4420.junit_sample.test.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import edu.westga.comp4420.junit_sample.model.Course;

public class CourseTest {
	
	@BeforeEach
	public void setup() {
		Course.clearCourses();
	}
	
	@Test
	public void testConstructorWithValidData() {
		Course course = new Course("CS", "4001", "2501", "Devops");
		assertEquals("CS", course.getPrefix());
		assertEquals("4001", course.getNumber());
		assertEquals("2501", course.getSemester());
		assertEquals("Devops", course.getNotes());
	}
	
	@Test
	public void testConstructorWithEmptyNotes() {
		Course course = new Course("CS", "4001", "2501", "");
		assertEquals("CS", course.getPrefix());
		assertEquals("4001", course.getNumber());
		assertEquals("2501", course.getSemester());
		assertEquals("", course.getNotes());
	}
	
	@Test
	public void testInvalidConstructor() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Course(null, "4001", "2501", "devops");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Course("", "4001", "2501", "devops");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Course("CS", null, "2501", "devops");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Course("CS", "", "2501", "devops");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Course("CS", "4001", null, "devops");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			new Course("CS", "4001", "", "devops");
		});
	}
	
	@Test
	public void testAddCourseCorrectly() {
		Course course = new Course("CS", "4001", "2501", "Devops");
		assertTrue(course.addCourse());
		assertEquals(1, Course.getAllCourses().size());
	}
	
	@Test
	public void testAddExactDuplicateCourseFails() {
		Course course1 = new Course("CS", "4001", "2501", "Devops");
		Course course2 = new Course("CS", "4001", "2501", "Devops");
		course1.addCourse();
		assertFalse(course2.addCourse());
	}
	
	@Test
	public void testAddDuplicateCourseDifferentSemester() {
		Course course1 = new Course("CS", "4001", "2401", "Devop");
		Course course2 = new Course("CS", "4001", "2501", "Devops");
		course1.addCourse();
		assertTrue(course2.addCourse());
	}
	
	@Test
	public void testAddDuplicateCourseWithDifferentPrefix() {
		Course course1 = new Course("CS", "4001", "2401", "Devop");
		Course course2 = new Course("SCI", "4001", "2401", "Devops");
		course1.addCourse();
		assertTrue(course2.addCourse());
	}
	
	@Test
	public void testAddDuplicateCourseWithDifferentNumber() {
		Course course1 = new Course("CS", "4000", "2401", "Devop");
		Course course2 = new Course("CS", "4001", "2401", "Devops");
		course1.addCourse();
		assertTrue(course2.addCourse());
	}
	
	@Test
	public void testToString(){
		Course course = new Course("CS", "4001", "2501", "Devops");
		String expected = "CS 4001 2501";
		assertEquals(expected, course.toString());
	}
	
	@Test
	public void testInvalidSemesterFormat(){
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Course("CS", "4001", "Spring 2025", "devops");
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Course("CS", "4001", "2500", "devops");
		});
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Course("CS", "4001", "2504", "devops");
		});
	
	
	}
	
	
	@Test
	public void testSetNotes() {
		Course course = new Course("CS", "4001", "2501", "Devops");
		String newNotes = "Test";
		
		course.setNotes(newNotes);
		
		assertEquals(newNotes, course.getNotes(), "The notes should be updated");
	
	}
	
	@Test
	public void testFindCourseSuccess() {
		Course course1 = new Course("CS", "4001", "2501", "Devops");
		Course course2 = new Course("CS", "4000", "2502", "Devops");
		course1.addCourse();
		course2.addCourse();
		
		String courseName = "CS 4001 2501";
		Course course = Course.findCourseByName(courseName);
		
		assertNotNull(course, "Course should be found.");
        assertEquals("CS", course.getPrefix(), "The prefix should be 'CS'.");
        assertEquals("4001", course.getNumber(), "The number should be '4001'.");
        assertEquals("2501", course.getSemester(), "The semester should be '2501'.");
	}
	
	@Test
	public void testFindCourseFail() {
		Course course1 = new Course("CS", "4001", "2501", "Devops");
		Course course2 = new Course("CS", "4000", "2502", "Devops");
		course1.addCourse();
		course2.addCourse();
		
		String courseName = "CS 4002 2501";
		Course course = Course.findCourseByName(courseName);
		
		assertNull(course, "Course should be found.");
	}
		
}