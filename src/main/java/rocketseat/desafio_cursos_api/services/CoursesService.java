package rocketseat.desafio_cursos_api.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocketseat.desafio_cursos_api.dto.CourseDTO;
import rocketseat.desafio_cursos_api.exceptions.CourseConflictException;
import rocketseat.desafio_cursos_api.exceptions.CourseNotFoundException;
import rocketseat.desafio_cursos_api.model.Course;
import rocketseat.desafio_cursos_api.repo.CourseRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class CoursesService {

    @Autowired
    private CourseRepo courseRepo;

    public Course createCourse(CourseDTO courseDTO) {
        Course newCourse = new Course();
        newCourse.setName(courseDTO.name());
        newCourse.setActive(Course.ActiveStatus.ACTIVE);
        newCourse.setCategory(courseDTO.category());
        return courseRepo.save(newCourse);
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public List<Course> getCoursesByNameOrCategory(String name, String category) {
        List<Course> courses;
        if (name == null) {
            courses = courseRepo.findCourseByCategory(category);
            return courses;
        }
        courses = courseRepo.findCourseByName(name);
        if (courses.isEmpty()) {
            throw new CourseNotFoundException("Course not found");
        }
        return courses;

    }

    public Course updateCourse(CourseDTO courseDTO, UUID id) {
        String newName = courseDTO.name();
        String category = courseDTO.category();
        Course updatedCourse = courseRepo.findCourseById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        if (newName == null && category == null) {
            throw new CourseConflictException("deve estar preenchido 'name' e/ou 'category'");
        }
        if (category == null) {
            updatedCourse.setName(newName);
        } else if (newName == null) {
            updatedCourse.setCategory(category);
        } else {
            updatedCourse.setName(newName);
            updatedCourse.setCategory(category);
        }
        return courseRepo.save(updatedCourse);
    }

    public void deleteCourse (UUID id) {
        Course course = courseRepo.findCourseById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        courseRepo.deleteById(course.getId());
    }

    public void toggleActiveCourse (UUID id) {
        Course course = courseRepo.findCourseById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));

        if (course.getStatus() == Course.ActiveStatus.ACTIVE) {
            course.setActive(Course.ActiveStatus.INACTIVE);
        } else {
            course.setActive(Course.ActiveStatus.ACTIVE);
        }
        courseRepo.save(course);
    }
}
