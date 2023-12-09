package telran.college.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.college.entities.*;

public interface LecturerRepo extends JpaRepository<Lecturer, Long> {

}
