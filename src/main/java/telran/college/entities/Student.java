package telran.college.entities;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import telran.college.dto.PersonDto;

@Entity
@NoArgsConstructor
public class Student extends Person {
public Student(PersonDto personDto) {
	super(personDto);
}
}
