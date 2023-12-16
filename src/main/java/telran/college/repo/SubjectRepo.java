package telran.college.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.college.dto.NameScore;
import telran.college.entities.*;

public interface SubjectRepo extends JpaRepository<Subject, Long> {
	
	
}
