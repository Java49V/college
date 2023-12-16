package telran.college.dto;

import jakarta.validation.constraints.*;

public record SubjectDto(@NotNull @Positive long id, String name,
		@NotNull @Min(50) @Max(300) int hours, @Positive Long lecturerId, SubjectType type) {

}
