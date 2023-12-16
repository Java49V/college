package telran.college.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.college.dto.*;
import telran.college.entities.*;

public interface LecturerRepo extends JpaRepository<Lecturer, Long> {
@Query(value="select lecturer.name as name, sum(hours) as hours from "
		+ "Subject subject  "
		+ "group by lecturer.name order by sum(hours) desc limit :nLecturers")
	List<LecturerHours> findLecturersMostHours(int nLecturers);
/******************************************************************/
List<NamePhone> findByCity(String city);

}
