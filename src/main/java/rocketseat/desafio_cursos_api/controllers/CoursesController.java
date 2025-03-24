package rocketseat.desafio_cursos_api.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocketseat.desafio_cursos_api.dto.CourseDTO;
import rocketseat.desafio_cursos_api.dto.ErrorMessage;
import rocketseat.desafio_cursos_api.exceptions.CourseConflictException;
import rocketseat.desafio_cursos_api.exceptions.CourseNotFoundException;
import rocketseat.desafio_cursos_api.model.Course;
import rocketseat.desafio_cursos_api.services.CoursesService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
public class CoursesController {


    @Autowired
    private CoursesService coursesService;


    @PostMapping("/")
    public ResponseEntity<Course> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        try{
            Course course = coursesService.createCourse(courseDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(course);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getCourseByNameOrCategory(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category
    ) {
        try {
            List<Course> courses;
            if (name == null && category == null) {
                courses = coursesService.getAllCourses();
            } else {
                courses = coursesService.getCoursesByNameOrCategory(name, category);
            }
            return ResponseEntity.ok().body(courses);
        }
        catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(
            @PathVariable UUID id,
            @RequestBody CourseDTO courseDTO
            ) {
        try {
            Course updatedCorse = coursesService.updateCourse(courseDTO, id);
            return ResponseEntity.ok().body(updatedCorse);
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
        } catch (CourseConflictException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable UUID id) {
        try{
            coursesService.deleteCourse(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
        }
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<?> activateCourse(@PathVariable UUID id) {
        try {
            coursesService.toggleActiveCourse(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (CourseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorMessage(e.getMessage()));
        }
    }

}
