package rocketseat.desafio_cursos_api.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocketseat.desafio_cursos_api.dto.CourseDTO;
import rocketseat.desafio_cursos_api.exceptions.CourseNotFoundException;
import rocketseat.desafio_cursos_api.model.Course;
import rocketseat.desafio_cursos_api.repo.CourseRepo;

import java.util.List;


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

}
