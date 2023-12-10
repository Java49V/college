package telran.college.repo;

import org.hibernate.mapping.List;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.college.entities.*;

public interface LecturerRepo extends JpaRepository<Lecturer, Long> {
	@Query("SELECT l FROM Lecturer l ORDER BY l.totalHours DESC")
    List<Lecturer> findTopLecturersWithMostHours(Pageable pageable);
}
