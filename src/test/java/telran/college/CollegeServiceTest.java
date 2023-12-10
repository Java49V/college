package telran.college;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.hibernate.mapping.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.yaml.snakeyaml.error.Mark;

import telran.college.dto.StudentMark;
import telran.college.entities.Lecturer;
import telran.college.entities.Student;
import telran.college.service.CollegeService;
@SpringBootTest
@Sql(scripts = {"db_test.sql"})
class CollegeServiceTest {
@Autowired
CollegeService collegeService;
	@Test
	void bestStudentsTypeTest() {
		List<String> students = collegeService.bestStudentsSubjectType("BACK_END", 2);
		String[] expected = {
				"David", "Yosef"
		};
		assertArrayEquals(expected, students.toArray(String[]::new));
	}
//	@Test
//	void studentsAvgScoreTest() {
//		List<StudentMark> studentMarks = collegeService.studentsAvgMarks();
//		studentMarks.forEach(sm -> System.out.printf("student: %s, avg score: %d\n",
//				sm.getName(), sm.getScore()));
//	}
	
	@Test
	void studentsAvgScoreTest() {
	    List<StudentMark> studentMarks = collegeService.studentsAvgMarks();
	    assertNotNull(studentMarks);
	    assertFalse(studentMarks.isEmpty());
	    studentMarks.forEach(sm -> {
	        assertNotNull(sm.getName());
	        assertTrue(sm.getScore() >= 0);
	    });
	}
	
	@Test
	void testFindTopLecturers() {
	    int count = 3; // Assuming you want the top 3 lecturers
	    List<Lecturer> lecturers = collegeService.findTopLecturers(count);
	    assertNotNull(lecturers);
	    assertEquals(count, lecturers.size());
	    assertTrue(lecturers.get(0).getTotalHours() >= lecturers.get(1).getTotalHours());
	}

	@Test
	void testFindStudentsWithLessThanTotalScore() {
	    long scoreThreshold = 150;
	    List<Student> students = collegeService.findStudentsWithLessThanTotalScore(scoreThreshold);
	    assertNotNull(students);
	    students.forEach(student -> {
	        long totalScore = student.getMarks().stream().mapToInt(Mark::getScore).sum();
	        assertTrue(totalScore < scoreThreshold);
	    });
	}
	
	@Test
	void testFindStudentsBornInMonth() {
	    int month = 1; // January, for example
	    List<Student> students = collegeService.findStudentsBornInMonth(month);
	    assertNotNull(students);
	    students.forEach(student -> assertEquals(month, student.getBirthDate().getMonthValue()));
	}
	
	@Test
	void testGetStudentSubjectsAndScores() {
	    String studentName = "David"; // Example student name
	    Map<String, Integer> subjectsAndScores = collegeService.getStudentSubjectsAndScores(studentName);
	    assertNotNull(subjectsAndScores);
	    assertFalse(subjectsAndScores.isEmpty());
	    subjectsAndScores.forEach((subject, score) -> {
	        assertNotNull(subject);
	        assertTrue(score >= 0);
	    });
	}
	
	@Test
	void testGetLecturersFromCity() {
	    String city = "Rehovot"; // Example city
	    List<Lecturer> lecturers = collegeService.getLecturersFromCity(city);
	    assertNotNull(lecturers);
	    lecturers.forEach(lecturer -> assertEquals(city, lecturer.getCity()));
	}

}
