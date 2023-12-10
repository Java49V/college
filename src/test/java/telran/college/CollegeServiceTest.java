package telran.college;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import telran.college.dto.*;
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
		List<NameScore> studentMarks = collegeService.studentsAvgMarks();
		String [] students = {
			"David", "Rivka", "Vasya", "Sara", "Yosef"	
		};
		int [] scores = {
				96, 95, 83, 80, 78
		};
		NameScore[] studentMarksArr = studentMarks.toArray(NameScore[]::new);
		IntStream.range(0, students.length)
		.forEach(i -> {
			assertEquals(students[i], studentMarksArr[i].getName());
			assertEquals(scores[i], studentMarksArr[i].getScore());
		});
	}
	@Test
	void lecturersMostHoursTest() {
		List<LecturerHours> lecturersHours = collegeService.lecturersMostHours(2);
		LecturerHours[] lecturersHoursArr = lecturersHours.toArray(LecturerHours[]::new);
		String[] lecturers = {
				"Abraham", "Mozes"
		};
		int [] hours = {
			225, 130	
		};
		IntStream.range(0, hours.length).forEach(i -> {
			assertEquals(lecturers[i], lecturersHoursArr[i].getName());
			assertEquals(hours[i], lecturersHoursArr[i].getHours());
		});
	}
	@Test
	void studentsScoresLessTest() {
		List<StudentCity> studentCityList = collegeService.studentsScoresLess(1);
		assertEquals(1, studentCityList.size());
		StudentCity studentCity = studentCityList.get(0);
		assertEquals("Rehovot",studentCity.getCity());
		assertEquals("Yakob",studentCity.getName());
	}
	@Test
	void studentsBurnMonthTest() {
		String [] namesExpected = {
				"Vasya", "Yakob"
		};
		String [] phonesExpected = {
			"054-1234567", "051-6677889"	
		};
		NamePhone[] studentPhonesArr = collegeService.studentsBurnMonth(10)
				.toArray(NamePhone[]::new);
		assertEquals(phonesExpected.length, studentPhonesArr.length);
		IntStream.range(0,  phonesExpected.length).forEach(i -> {
			assertEquals(namesExpected[i], studentPhonesArr[i].getName());
			assertEquals(phonesExpected[i], studentPhonesArr[i].getPhone());
		});
	}
	@Test
	void lecturesCityTest() {
		String[]expectedNames = {
				"Abraham", "Mozes"
		};
		String[] expectedPhones = {
			"050-1111122", "054-3334567"	
		};
		NamePhone[] namePhones = collegeService.lecturersCity("Jerusalem")
				.toArray(NamePhone[]::new);
		assertEquals(expectedNames.length, namePhones.length);
		IntStream.range(0, namePhones.length).forEach(i -> {
			assertEquals(expectedNames[i], namePhones[i].getName());
			assertEquals(expectedPhones[i], namePhones[i].getPhone());
		});
	}
	@Test
	void subjectsScoresTest() {
		String[] subjects = {
				"Java Core", "Java Technologies", "HTML/CSS", "JavaScript", "React"
		};
		int[] scores = {
				75, 60, 95, 85, 100
		};
		NameScore[] subjectScores = collegeService.subjectsScores("Vasya")
				.toArray(NameScore[]::new);
		assertEquals(scores.length, subjectScores.length);
		IntStream.range(0, scores.length).forEach(i -> {
			assertEquals(subjects[i], subjectScores[i].getName());
			assertEquals(scores[i], subjectScores[i].getScore());
		});
		
	}

}
