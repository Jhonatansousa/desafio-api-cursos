package rocketseat.desafio_cursos_api.exceptions;

public class CourseConflictException extends RuntimeException {
    public CourseConflictException(String message) {
        super(message);
    }
}
