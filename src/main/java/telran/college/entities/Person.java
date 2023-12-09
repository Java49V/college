package telran.college.entities;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "students_lecturers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter
public abstract class Person {
	@Id
   long id;
	@Column(nullable = false)
	String name;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false, name = "birth_date")
	LocalDate birthDate;
	String city;
	String phone;
}
