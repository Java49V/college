package telran.college.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.college.dto.*;
import telran.college.entities.*;

public interface StudentRepo extends JpaRepository<Student, Long> {
	String JOIN_STUDENTS_MARKS = "FROM students_lecturers st join marks m on stid=st.id ";
	String JOIN_ALL = JOIN_STUDENTS_MARKS
			+ "join subjects sb on sb.id=suid ";
	/**********************/
	@Query(value="SELECT st.name as name " + JOIN_ALL + "where type=:type "
			+ "group by st.name order by avg(score) desc limit :nStudents", nativeQuery = true)
List<String> findBestStudentsSubjectType(String type, int nStudents);
	/*************************/
	@Query(value="SELECT st.name as name, round(avg(score)) as score " + 
JOIN_STUDENTS_MARKS + "group by st.name order by avg(score) desc", nativeQuery=true)
	List<NameScore> studentsMarks();
	/*********************************/
	@Query(value="SELECT st.name as name, city "
			+ "from (select * from students_lecturers where dtype='Student') st "
			+ "left join marks on st.id=stid "
			+ "group by st.name, city having count(score) < :scoresThreshold", nativeQuery=true)
	List<StudentCity> findStudentsScoresLess(int scoresThreshold);
	/*************************************/
	@Query(value="SELECT name, phone from students_lecturers"
			+ " where EXTRACT (MONTH FROM birth_date) = :month and dtype='Student'",
			nativeQuery = true)
	List<NamePhone> findStudentsBurnMonth(int month);
	/************************************************/
	@Query(value="SELECT sb.name as name, score " + JOIN_ALL +
			"where st.name=:studentName", nativeQuery=true)
	List<NameScore> findSubjectScore(String studentName);
}
