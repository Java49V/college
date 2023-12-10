package telran.college.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.college.dto.*;
import telran.college.entities.*;

public interface LecturerRepo extends JpaRepository<Lecturer, Long> {
@Query(value="select sl.name as name, sum(hours) as hours from "
		+ "students_lecturers sl join subjects sb on sl.id=sb.lecturer_id "
		+ "group by sl.name order by sum(hours) desc limit :nLecturers", nativeQuery=true)
	List<LecturerHours> findLecturersMostHours(int nLecturers);
/******************************************************************/
@Query(value="select name, phone from students_lecturers where city=:city and dtype='Lecturer'", nativeQuery = true)
List<NamePhone> findLecturersCity(String city);

}
