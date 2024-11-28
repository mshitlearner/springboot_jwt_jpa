package in.mshitlearner.service;

import java.util.List;

import in.mshitlearner.dto.CourseDTO;

public interface CourseService {
	  public String insertOrUpdateCourse(CourseDTO Course);
	  public List<CourseDTO> getAllCourses();
	  public String deleteBook(Integer courseId);
	  public CourseDTO getCoursesById(Integer courseId);
}
