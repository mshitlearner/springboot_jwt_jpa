package in.mshitlearner.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.mshitlearner.entity.Courses;

public interface CoursesRepository extends JpaRepository<Courses, Integer> {

}
