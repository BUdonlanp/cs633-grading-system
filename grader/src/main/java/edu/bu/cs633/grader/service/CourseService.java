package edu.bu.cs633.grader.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.bu.cs633.grader.entity.Assignment;
import edu.bu.cs633.grader.entity.Course;
import edu.bu.cs633.grader.entity.CourseSemester;
import edu.bu.cs633.grader.entity.Enrollment;
import edu.bu.cs633.grader.entity.Grade;
import edu.bu.cs633.grader.entity.Semester;
import edu.bu.cs633.grader.entity.Student;
import edu.bu.cs633.grader.entity.Teacher;
import edu.bu.cs633.grader.repository.CourseRepository;
import edu.bu.cs633.grader.repository.CourseSemesterRepository;
import edu.bu.cs633.grader.repository.EnrollmentRepository;
import edu.bu.cs633.grader.repository.GradeRepository;
import edu.bu.cs633.grader.repository.SemesterRepository;

/**
 * This provides helpful methods for creating/working with Courses/Semsters
 * 
 * @author donlanp
 * 
 */
@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private SemesterRepository semesterRepository;
	@Autowired
	private CourseSemesterRepository courseSemesterRepository;
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	@Autowired
	private GradeRepository gradeRepo;

	public boolean courseExists(String courseCode) {
		Course course = courseRepository.findByCourseCode(courseCode);

		return course != null;
	}

	/**
	 * Creates a course based on the parameters given
	 * 
	 * @param courseCode
	 * @param courseName
	 * @return The created course object within the database.
	 */
	public Course createCourse(String courseCode, String courseName) {

		Course course = null;
		try {
			course = new Course();
			course.setCourseCode(courseCode);
			course.setCourseName(courseName);

			course = courseRepository.save(course);
		} catch (Exception e) {
			System.out.println("Exception thrown: " + e.getClass() + " --- "
					+ e.getMessage());
		}
		return course;
	}

	public List<Course> getAllCourses() {
		List<Course> courses = new ArrayList<Course>();

		Iterable<Course> result = courseRepository.findAll();
		Iterator<Course> iter = result.iterator();
		while (iter.hasNext()) {
			courses.add(iter.next());
		}

		return courses;
	}

	public boolean semesterExists(int year, String name) {
		Semester semester = semesterRepository.findByYearAndSemesterName(year,
				name);

		return semester != null;
	}

	public Semester createSemester(int year, String name) {
		Semester semester = null;
		try {
			semester = new Semester();
			semester.setYear(year);
			semester.setSemesterName(name);

			semester = semesterRepository.save(semester);

		} catch (Exception e) {
			System.out.println("Exception thrown: " + e.getClass() + " --- "
					+ e.getMessage());
		}
		return semester;
	}

	public List<Semester> getAllSemesters() {
		List<Semester> courses = new ArrayList<Semester>();

		Iterable<Semester> result = semesterRepository.findAll();
		Iterator<Semester> iter = result.iterator();
		while (iter.hasNext()) {
			courses.add(iter.next());
		}

		return courses;
	}

	public boolean courseInstanceAlreadyExists(Teacher teacher, Course course,
			Semester semester) {
		CourseSemester courseSemester = courseSemesterRepository
				.findByTeacherAndCourseAndSemester(teacher, course, semester);

		return courseSemester != null;
	}

	public CourseSemester createCourseInstance(Teacher teacher, Course course,
			Semester semester) {
		CourseSemester instance = null;
		try {
			instance = new CourseSemester();
			instance.setTeacher(teacher);
			instance.setCourse(course);
			instance.setSemester(semester);

			instance = courseSemesterRepository.save(instance);

		} catch (Exception e) {
			System.out.println("Exception thrown: " + e.getClass() + " --- "
					+ e.getMessage());
		}
		return instance;
	}
	
	
	public List<CourseSemester> getAllAvailableCourseInstances(){
		List<CourseSemester> courseInstances = new ArrayList<CourseSemester>();
		
		Iterable<CourseSemester> foundItems = courseSemesterRepository.findAll();
		Iterator<CourseSemester> iter = foundItems.iterator();
		
		while(iter.hasNext()){
			courseInstances.add(iter.next());
		}
		
		return courseInstances;
	}
	
	public List<Enrollment> enrollStudentsInCourse(CourseSemester instance, List<Student> students){
		List<Enrollment> newEnrollments = new ArrayList<Enrollment>();
		
		for(Student s: students){			
			try {
				Enrollment enroll = new Enrollment();
				enroll.setCourseSemester(instance);
				enroll.setStudent(s);
				
				enrollmentRepository.save(enroll);
				
				//Set grade for each assignment in that course
				for(Assignment a: instance.getAssignments()){
					Grade g = new Grade();
					g.setAssignment(a);
					g.setEnrollment(enroll);
					
					gradeRepo.save(g);
				}
			} catch (Exception e){
				System.out.println("Could not enroll " + s);
				System.out.println("Error was: " + e.getMessage());
			}
		}
		
		return newEnrollments;
	}
	
	public List<CourseSemester> getCoursesForTeacher(Teacher teach){
		List<CourseSemester> courses = new ArrayList<CourseSemester>();
		courses.addAll(courseSemesterRepository.findByTeacher(teach));
		return courses;
	}

	public List<Enrollment> getStudentEnrollments(Student s){
		return enrollmentRepository.findByStudent(s);
	}
	
}
