package telran.college.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.college.dto.StudentMark;
import telran.college.entities.*;

public interface StudentRepo extends JpaRepository<Student, Long> {
	String JOIN_STUDENTS_MARKS = "FROM students_lecturers st join marks m on stid=st.id ";
	String JOIN_ALL = JOIN_STUDENTS_MARKS
			+ "join subjects sb on sb.id=suid ";
	@Query(value="SELECT st.name as name " + JOIN_ALL + "where type=:type "
			+ "group by st.name order by avg(score) desc limit :nStudents", nativeQuery = true)
List<String> findBestStudentsSubjectType(String type, int nStudents);
	@Query(value="SELECT st.name as name, round(avg(score)) as score " + 
JOIN_STUDENTS_MARKS + "group by st.name order by avg(score) desc", nativeQuery=true)
	List<StudentMark> studentsMarks();
}
