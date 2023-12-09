package telran.college;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import telran.college.dto.StudentMark;
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
	@Test
	void studentsAvgScoreTest() {
		List<StudentMark> studentMarks = collegeService.studentsAvgMarks();
		studentMarks.forEach(sm -> System.out.printf("student: %s, avg score: %d\n",
				sm.getName(), sm.getScore()));
	}

}
