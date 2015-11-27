package edu.bu.cs633.grader.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.bu.cs633.grader.entity.Assignment;
import edu.bu.cs633.grader.entity.CourseSemester;
import edu.bu.cs633.grader.entity.Enrollment;
import edu.bu.cs633.grader.entity.Grade;
import edu.bu.cs633.grader.entity.Teacher;
import edu.bu.cs633.grader.repository.AssignmentRepository;
import edu.bu.cs633.grader.repository.CourseSemesterRepository;
import edu.bu.cs633.grader.repository.GradeRepository;

/**
 * Service class for handling Assignments
 * @author donlanp
 *
 */
@Service
public class AssignmentService {

	@Autowired
	private CourseSemesterRepository courseSemesterRepo;
	@Autowired
	private AssignmentRepository assignmentRepo;
	@Autowired
	private GradeRepository gradeRepo;
	
	@Autowired
	private CourseService courseService;
	
	
	public Assignment createAssignment(CourseSemester course, String name, int pointsAvailable){
		Assignment assignment = new Assignment();
		assignment.setCourseSemester(course);
		assignment.setAssignmentName(name);
		assignment.setAssignmentTotalPoints(pointsAvailable);
		
		assignmentRepo.save(assignment);
		
		//Create the grade for each student in that course
		for(Enrollment e: course.getEnrollments()){
			Grade g = new Grade();
			g.setAssignment(assignment);
			g.setEnrollment(e);
			
			gradeRepo.save(g);
		}
		
		return assignment;
	}
	
	public List<Assignment> getTeachersAssignments(Teacher teacher){
		List<Assignment> retList = new ArrayList<Assignment>();
		
		List<CourseSemester> teachersCourses = courseSemesterRepo.findByTeacher(teacher);
		
		for(CourseSemester cs: teachersCourses){
			retList.addAll(cs.getAssignments());
		}
		
		return retList;
	}

	public Assignment getAssignment(int assignmentId) {
		return assignmentRepo.findOne(assignmentId);
	}

	public void saveAssignment(Assignment selectedAssignment) {
		gradeRepo.save(selectedAssignment.getGrades());
	}
	
	public List<Grade> getGradesForEnrollment(Enrollment e){
		return gradeRepo.findByEnrollment(e);
	}
}
