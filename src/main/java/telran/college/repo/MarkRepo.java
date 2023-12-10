package telran.college.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.college.entities.*;

public interface MarkRepo extends JpaRepository<Mark, Long> {

}
