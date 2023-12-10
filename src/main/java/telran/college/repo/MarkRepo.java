package telran.college.repo;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;

import telran.college.entities.*;

public interface MarkRepo extends JpaRepository<Mark, Long> {
	 List<Mark> findByStudentName(String studentName);
}
