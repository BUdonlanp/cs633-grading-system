package edu.bu.cs633.grader.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;

import edu.bu.cs633.grader.entity.Assignment;
import edu.bu.cs633.grader.entity.CourseSemester;
import edu.bu.cs633.grader.service.AssignmentService;
import edu.bu.cs633.grader.service.CourseService;

@ManagedBean(name = "createAssignmentBean")
@RequestScoped
public class CreateAssignmentBean {
	
	@Autowired
	private AssignmentService assignmentService;
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UserSessionBean currentUser;
	
	//Form fields
	
	//Create Assignment
	private List<SelectItem> courseInstancesSelect;
	private List<CourseSemester> courseInstances;
	private int selectedCourseSemester;
	private String assignmentName;
	private int availablePoints = 100;
	
	//View Assignments
	private List<Assignment> assignments;
	
	public CreateAssignmentBean(){
	}


	/**
	 * Initializes variables
	 */
	@PostConstruct
	public void init() {
		if(!getCurrentUser().getIsTeacher()){
			return;
		}
		//This works because we know the current use is a teacher
		setCourseInstances(courseService.getCoursesForTeacher(getCurrentUser().getCurrentUser().getTeacher()));
		courseInstancesSelect = new ArrayList<SelectItem>();
		for(CourseSemester cs: getCourseInstances()){
			courseInstancesSelect.add(new SelectItem(cs.getCourseSemesterId(), cs.toString()));
		}
		
		setAssignments(assignmentService.getTeachersAssignments(getCurrentUser().getCurrentUser().getTeacher()));
	}
	
	//Events
	
	public void saveAssignment(){
		CourseSemester selectedCourse = null;
		for(CourseSemester cs : courseInstances){
			if(cs.getCourseSemesterId() == selectedCourseSemester){
				selectedCourse = cs;
				break;
			}
		}
		
		assignmentService.createAssignment(selectedCourse, getAssignmentName(), getAvailablePoints());
		//Refresh page
		init();
	}
	
	public void editGrades(int assignmentId){
		System.out.println(assignmentId);
		
		for(Assignment a: getAssignments()){
			if(a.getAssignmentId() == assignmentId){
				getCurrentUser().setSelectedAssignment(a);
				break;
			}
		}
	}
	
	public void saveGrades(){
		assignmentService.saveAssignment(getCurrentUser().getSelectedAssignment());
	}


	//Getters and Setters
	
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
	 * @return the assignmentName
	 */
	public String getAssignmentName() {
		return assignmentName;
	}


	/**
	 * @param assignmentName the assignmentName to set
	 */
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}


	/**
	 * @return the availablePoints
	 */
	public int getAvailablePoints() {
		return availablePoints;
	}


	/**
	 * @param availablePoints the availablePoints to set
	 */
	public void setAvailablePoints(int availablePoints) {
		this.availablePoints = availablePoints;
	}


	/**
	 * @return the assignments
	 */
	public List<Assignment> getAssignments() {
		return assignments;
	}


	/**
	 * @param assignments the assignments to set
	 */
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}


	/**
	 * @return the currentUser
	 */
	public UserSessionBean getCurrentUser() {
		return currentUser;
	}


	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(UserSessionBean currentUser) {
		this.currentUser = currentUser;
	}

}
