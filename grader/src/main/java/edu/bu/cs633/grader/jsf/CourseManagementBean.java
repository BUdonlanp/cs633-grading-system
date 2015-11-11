package edu.bu.cs633.grader.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;

import edu.bu.cs633.grader.entity.Course;
import edu.bu.cs633.grader.entity.CourseSemester;
import edu.bu.cs633.grader.entity.Semester;
import edu.bu.cs633.grader.entity.Student;
import edu.bu.cs633.grader.entity.Teacher;
import edu.bu.cs633.grader.service.CourseService;
import edu.bu.cs633.grader.service.UserService;

/**
 * @author donlanp
 * 
 */
@ManagedBean(name = "courseManageBean")
@RequestScoped
public class CourseManagementBean {

	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;

	private int year = 2015;
	private String selectSemester;
	
	private String courseCode;
	private String courseName;
	
	private int selectedCourse;
	private int selectedSemester;
	private int selectedTeacher;
	
	private int selectedCourseSemester;
	private int enrollmentCourseSemester;
	
	private List<Course> availableCourses;
	private List<Semester> availableSemesters;
	private List<Teacher> availableTeachers;
	
	private List<SelectItem> courseSelect;
	private List<SelectItem> semesterSelect;
	private List<SelectItem> teacherSelect;
	
	private List<SelectItem> courseInstancesSelect;
	
	private List<CourseSemester> courseInstances;
	
	private List<Student> allStudents;
	private List<SelectItem> studentSelect;
	private List<String> selectedStudents;
	
	@PostConstruct
	public void init(){
		//Populate drop downs
		setAvailableCourses(courseService.getAllCourses());
		setAvailableSemesters(courseService.getAllSemesters());
		setAvailableTeachers(userService.getAllTeachers());
		setAllStudents(userService.getAllStudents());
		
		//Populate our grid
		setCourseInstances(courseService.getAllAvailableCourseInstances());
		
		createSelectItems();
	}
	
	private void createSelectItems(){
		courseSelect = new ArrayList<SelectItem>();
		semesterSelect = new ArrayList<SelectItem>();
		teacherSelect = new ArrayList<SelectItem>();
		
		courseInstancesSelect = new ArrayList<SelectItem>();
		
		studentSelect = new ArrayList<SelectItem>();
		
		for(Course c: getAvailableCourses()){
			courseSelect.add(new SelectItem(c.getCourseId(), c.getCourseCode() + ":" + c.getCourseName()));
		}
		for(Semester s: getAvailableSemesters()){
			semesterSelect.add(new SelectItem(s.getSemesterId(), s.getYear() + "-" + s.getSemesterName()));
		}
		for(Teacher t: getAvailableTeachers()){
			teacherSelect.add(new SelectItem(t.getTeacherId(), t.getUser().getLastName() + "," + t.getUser().getFirstName()));
		}
		for(CourseSemester cs: getCourseInstances()){
			courseInstancesSelect.add(new SelectItem(cs.getCourseSemesterId(), cs.toString()));
		}
		for(Student st: getAllStudents()){
			studentSelect.add(new  SelectItem(st.getStudentId(), st.toString()));
		}
	}

	public String createSemester(){
		if(courseService.semesterExists(getYear(), getSelectSemester())){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cannot create a Semester with year: " + year + " and semester: " + selectSemester, null));			
		} else {
			courseService.createSemester(getYear(), getSelectSemester());
			setAvailableSemesters(courseService.getAllSemesters());
		}
		
		//Refresh all data
		init();
		return null;
	}
	
	public String createCourse(){
		if(courseService.courseExists(getCourseCode())){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The course with the course code " + getCourseCode() + " already exists!", null));			
		} else {
			courseService.createCourse(getCourseCode(), getCourseName());
			setAvailableCourses(courseService.getAllCourses());
		}
		
		//Refresh all data
		init();
		
		return null;
	}
	
	public String createCourseInstance(){
		Teacher teacher = null;
		for(Teacher t: getAvailableTeachers()){
			if(t.getTeacherId() == selectedTeacher){
				teacher = t;
			}
		}
		Course course = null;
		for(Course t: getAvailableCourses()){
			if(t.getCourseId() == selectedCourse){
				course = t;
			}
		}
		Semester semester = null;
		for(Semester t: getAvailableSemesters()){
			if(t.getSemesterId() == selectedSemester){
				semester = t;
			}
		}
		
		
		if(courseService.courseInstanceAlreadyExists(teacher, course, semester)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The instance already exists!", null));			
		} else {
			courseService.createCourseInstance(teacher, course, semester);
		}
		
		//Refresh all data
		init();
		
		return null;
	}
	
	/**
	 * Enroll the selected students into the selected course.
	 */
	public String enrollStudents(){
		List<Student> studentsToEnroll = new ArrayList<Student>();
		
		for(String id: selectedStudents){
			studentsToEnroll.add(userService.getStudentById(Integer.parseInt(id)));
		}
		CourseSemester selected = null;
		for(CourseSemester cs: getCourseInstances()){
			if(cs.getCourseSemesterId() == enrollmentCourseSemester){
				selected = cs;
			}
		}
		
		courseService.enrollStudentsInCourse(selected, studentsToEnroll);		
		//Refresh all data
		init();
		return null;
	}
	
	//Getters and Setters

	/**
	 * @return the selectSemester
	 */
	public String getSelectSemester() {
		return selectSemester;
	}

	/**
	 * @param selectSemester
	 *            the selectSemester to set
	 */
	public void setSelectSemester(String selectSemester) {
		this.selectSemester = selectSemester;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * @param courseCode the courseCode to set
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the selectedCourse
	 */
	public int getSelectedCourse() {
		return selectedCourse;
	}

	/**
	 * @param selectedCourse the selectedCourse to set
	 */
	public void setSelectedCourse(int selectedCourse) {
		this.selectedCourse = selectedCourse;
	}

	/**
	 * @return the selectedSemester
	 */
	public int getSelectedSemester() {
		return selectedSemester;
	}

	/**
	 * @param selectedSemester the selectedSemester to set
	 */
	public void setSelectedSemester(int selectedSemester) {
		this.selectedSemester = selectedSemester;
	}

	/**
	 * @return the selectedTeacher
	 */
	public int getSelectedTeacher() {
		return selectedTeacher;
	}

	/**
	 * @param selectedTeacher the selectedTeacher to set
	 */
	public void setSelectedTeacher(int selectedTeacher) {
		this.selectedTeacher = selectedTeacher;
	}

	/**
	 * @return the availableCourses
	 */
	public List<Course> getAvailableCourses() {
		return availableCourses;
	}

	/**
	 * @param availableCourses the availableCourses to set
	 */
	public void setAvailableCourses(List<Course> availableCourses) {
		this.availableCourses = availableCourses;
	}

	/**
	 * @return the availableSemesters
	 */
	public List<Semester> getAvailableSemesters() {
		return availableSemesters;
	}

	/**
	 * @param availableSemesters the availableSemesters to set
	 */
	public void setAvailableSemesters(List<Semester> availableSemesters) {
		this.availableSemesters = availableSemesters;
	}

	/**
	 * @return the availableTeachers
	 */
	public List<Teacher> getAvailableTeachers() {
		return availableTeachers;
	}

	/**
	 * @param availableTeachers the availableTeachers to set
	 */
	public void setAvailableTeachers(List<Teacher> availableTeachers) {
		this.availableTeachers = availableTeachers;
	}

	/**
	 * @return the courseSelect
	 */
	public List<SelectItem> getCourseSelect() {
		return courseSelect;
	}

	/**
	 * @param courseSelect the courseSelect to set
	 */
	public void setCourseSelect(List<SelectItem> courseSelect) {
		this.courseSelect = courseSelect;
	}

	/**
	 * @return the semesterSelect
	 */
	public List<SelectItem> getSemesterSelect() {
		return semesterSelect;
	}

	/**
	 * @param semesterSelect the semesterSelect to set
	 */
	public void setSemesterSelect(List<SelectItem> semesterSelect) {
		this.semesterSelect = semesterSelect;
	}

	/**
	 * @return the teacherSelect
	 */
	public List<SelectItem> getTeacherSelect() {
		return teacherSelect;
	}

	/**
	 * @param teacherSelect the teacherSelect to set
	 */
	public void setTeacherSelect(List<SelectItem> teacherSelect) {
		this.teacherSelect = teacherSelect;
	}

	/**
	 * @return the courseInstances
	 */
	public List<CourseSemester> getCourseInstances() {
		return courseInstances;
	}

	/**
	 * @param courseInstances the courseInstances to set
	 */
	public void setCourseInstances(List<CourseSemester> courseInstances) {
		this.courseInstances = courseInstances;
	}

	/**
	 * @return the courseInstancesSelect
	 */
	public List<SelectItem> getCourseInstancesSelect() {
		return courseInstancesSelect;
	}

	/**
	 * @param courseInstancesSelect the courseInstancesSelect to set
	 */
	public void setCourseInstancesSelect(List<SelectItem> courseInstancesSelect) {
		this.courseInstancesSelect = courseInstancesSelect;
	}

	/**
	 * @return the selectedCourseSemester
	 */
	public int getSelectedCourseSemester() {
		return selectedCourseSemester;
	}

	/**
	 * @param selectedCourseSemester the selectedCourseSemester to set
	 */
	public void setSelectedCourseSemester(int selectedCourseSemester) {
		this.selectedCourseSemester = selectedCourseSemester;
	}

	/**
	 * @return the allStudents
	 */
	public List<Student> getAllStudents() {
		return allStudents;
	}

	/**
	 * @param allStudents the allStudents to set
	 */
	public void setAllStudents(List<Student> allStudents) {
		this.allStudents = allStudents;
	}

	/**
	 * @return the studentSelect
	 */
	public List<SelectItem> getStudentSelect() {
		return studentSelect;
	}

	/**
	 * @param studentSelect the studentSelect to set
	 */
	public void setStudentSelect(List<SelectItem> studentSelect) {
		this.studentSelect = studentSelect;
	}

	/**
	 * @return the selectedStudents
	 */
	public List<String> getSelectedStudents() {
		return selectedStudents;
	}

	/**
	 * @param selectedStudents the selectedStudents to set
	 */
	public void setSelectedStudents(List<String> selectedStudents) {
		this.selectedStudents = selectedStudents;
	}

	/**
	 * @return the enrollmentCourseSemester
	 */
	public int getEnrollmentCourseSemester() {
		return enrollmentCourseSemester;
	}

	/**
	 * @param enrollmentCourseSemester the enrollmentCourseSemester to set
	 */
	public void setEnrollmentCourseSemester(int enrollmentCourseSemester) {
		this.enrollmentCourseSemester = enrollmentCourseSemester;
	}

}
