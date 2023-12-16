package telran.college.entities;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import telran.college.dto.PersonDto;

@Entity
@NoArgsConstructor
public class Lecturer extends Person {
  public Lecturer(PersonDto person) {
	  super(person);
  }
}
