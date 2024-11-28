package in.mshitlearner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.mshitlearner.dto.CourseDTO;
import in.mshitlearner.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/courses")
@CrossOrigin
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	

	
	@PostMapping(value="/addCourse", consumes = "application/json", produces = "application/json")
	@PreAuthorize("hasAuthority('ADMIN')")
	@Operation(summary = "Saving the Course Details", description = "Returns a msg as Saved Successfully for the user")
	@ApiResponses(
			value = { @ApiResponse(responseCode = "200", description = "Successfully/Unsuccessfully saved")
	})
	public String addCourses(@RequestBody CourseDTO Course) {
		String msg = courseService.insertOrUpdateCourse(Course);
		return msg;
	}

	@GetMapping(value="/getAllCourses")
	@PreAuthorize("hasAuthority('USER')")
	@Operation(summary = "Fetching the list of Courses", description = "Returns a List of Courses saved in the DB for the user")
	public List<CourseDTO> getAllCourses() {
		List<CourseDTO> lstCourse = courseService.getAllCourses();
		return lstCourse;
	}
	
	@GetMapping(value="/getCourse/{id}")
	@PreAuthorize("hasAuthority('USER')")
	@Operation(summary = "Fetching the selected Course", description = "Returns a Courses Details saved in the DB for the user")
	public CourseDTO getCoursesById(@PathVariable Integer id) {
		CourseDTO CourseDto = courseService.getCoursesById(id);
		return CourseDto;
	}

	@DeleteMapping(value="deleteCourse/{courseId}")
	@PreAuthorize("hasAuthority('ADMIN')")
	@Operation(summary = "Deleting the Course", description = "Returns a msg after deleting the specified course for the user")
	public String deleteBook(@Parameter(description = "ID of the Course to Delete") @PathVariable Integer courseId) {
		String msg = courseService.deleteBook(courseId);
		return msg;
	}
	
    
}
