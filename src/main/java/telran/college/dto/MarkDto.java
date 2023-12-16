package telran.college.dto;

import jakarta.validation.constraints.*;

public record MarkDto(@NotNull @Positive long studentId, @NotNull @Positive long subjectId,
		@NotNull @Min(60) @Max(100) int score) {

}
