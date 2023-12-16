package telran.college.entities;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.college.dto.PersonDto;
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
	protected Person(PersonDto personDto) {
		id = personDto.id();
		name = personDto.name();
		birthDate = personDto.birthDate();
		city = personDto.city();
		phone = personDto.phone();
	}
	public PersonDto build() {
		return new PersonDto(id, name, birthDate, city, phone);
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
