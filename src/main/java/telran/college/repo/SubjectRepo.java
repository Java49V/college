package telran.college.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.college.entities.*;

public interface SubjectRepo extends JpaRepository<Subject, Long> {

}
