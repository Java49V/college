package telran.college.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.*;

public record PersonDto(long id, String name, @Past LocalDate birthDate, String city, String phone) {
	
}
