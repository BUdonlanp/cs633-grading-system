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
import edu.bu.cs633.grader.entity.Semester;
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
	
	private List<Course> availableCourses;
	private List<Semester> availableSemesters;
	private List<Teacher> availableTeachers;
	
	private List<SelectItem> courseSelect;
	private List<SelectItem> semesterSelect;
	private List<SelectItem> teacherSelect;
	
	@PostConstruct
	public void init(){
		setAvailableCourses(courseService.getAllCourses());
		setAvailableSemesters(courseService.getAllSemesters());
		setAvailableTeachers(userService.getAllTeachers());
		
		createSelectItems();
	}
	
	private void createSelectItems(){
		courseSelect = new ArrayList<SelectItem>();
		semesterSelect = new ArrayList<SelectItem>();
		teacherSelect = new ArrayList<SelectItem>();
		
		for(Course c: getAvailableCourses()){
			courseSelect.add(new SelectItem(c.getCourseId(), c.getCourseCode() + ":" + c.getCourseName()));
		}
		for(Semester s: getAvailableSemesters()){
			semesterSelect.add(new SelectItem(s.getSemesterId(), s.getYear() + "-" + s.getSemesterName()));
		}
		for(Teacher t: getAvailableTeachers()){
			teacherSelect.add(new SelectItem(t.getTeacherId(), t.getUser().getLastName() + "," + t.getUser().getFirstName()));
		}
	}

	public void createSemester(){
		if(courseService.semesterExists(getYear(), getSelectSemester())){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cannot create a Semester with year: " + year + " and semester: " + selectSemester, null));			
		} else {
			courseService.createSemester(getYear(), getSelectSemester());
			setAvailableSemesters(courseService.getAllSemesters());
		}
	}
	
	public void createCourse(){
		if(courseService.courseExists(getCourseCode())){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The course with the course code " + getCourseCode() + " already exists!", null));			
		} else {
			courseService.createCourse(getCourseCode(), getCourseName());
			setAvailableCourses(courseService.getAllCourses());
		}
	}
	
	public void createCourseInstance(){
		Teacher teacher = null;
		for(Teacher t: getAvailableTeachers()){
			if(t.getTeacherId() == selectedTeacher){
				teacher = t;
			}
		}
		Course course = null;
		for(Course t: getAvailableCourses()){
			if(t.getCourseId() == selectedTeacher){
				course = t;
			}
		}
		Semester semester = null;
		for(Semester t: getAvailableSemesters()){
			if(t.getSemesterId() == selectedTeacher){
				semester = t;
			}
		}
		
		
		if(courseService.courseInstanceAlreadyExists(teacher, course, semester)){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The instance already exists!", null));			
		} else {
			courseService.createCourseInstance(teacher, course, semester);
		}
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

}
