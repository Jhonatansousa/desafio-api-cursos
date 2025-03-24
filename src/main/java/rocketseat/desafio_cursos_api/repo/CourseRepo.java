package rocketseat.desafio_cursos_api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.desafio_cursos_api.model.Course;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseRepo extends JpaRepository<Course, UUID> {

    public List<Course> findCourseByName(String name);
    public List<Course> findCourseByCategory(String category);
    Optional<Course> findCourseById(UUID id);
}
