package in.mshitlearner.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.mshitlearner.dto.CourseDTO;
import in.mshitlearner.entity.Courses;
import in.mshitlearner.repository.CoursesRepository;
import in.mshitlearner.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CoursesRepository repository;
	
	@Override
	public String insertOrUpdateCourse(CourseDTO courseDto) {
		// TODO Auto-generated method stub
		Courses courses = new Courses();
		if(courseDto.getCourseSeqId() != null)
			courseDto.setCourseSeqId(courseDto.getCourseSeqId());
		
		courses.setCourseName(courseDto.getCourseName());
		courses.setPrice(courseDto.getPrice());
		
		courses = repository.save(courses);
		if(courses.getCourseSeqId() > 0)
			return "Save/Updated Successfully";
		else
			return "UnSaved Successfully";
	}

	@Override
	public List<CourseDTO> getAllCourses() {
		// TODO Auto-generated method stub
		List<Courses> lstCourses = repository.findAll();
		List<CourseDTO> lstCoursesDto = lstCourses.stream().map((course) -> {
			CourseDTO courseDto = new CourseDTO();
			courseDto.setCourseSeqId(course.getCourseSeqId());
			courseDto.setCourseName(course.getCourseName());
			courseDto.setPrice(course.getPrice());
			return courseDto;
		}).collect(Collectors.toList());
		return lstCoursesDto;
	}

	@Override
	public String deleteBook(Integer courseId) {
		// TODO Auto-generated method stub
		Optional<Courses> course = repository.findById(courseId);
		if(course.isPresent()) {
			repository.delete(course.get());
			return "Deleted Successfully";
		}
		return "Deleted unsuccessfully";	
	}

	@Override
	public CourseDTO getCoursesById(Integer courseId) {
		CourseDTO  courseDto  = new CourseDTO ();
		Optional<Courses> course = repository.findById(courseId);
		if(course.isPresent()) {
			courseDto.setCourseSeqId(course.get().getCourseSeqId());
			courseDto.setCourseName(course.get().getCourseName());
			courseDto.setPrice(course.get().getPrice());
		}
		return courseDto;
	}
	
}
