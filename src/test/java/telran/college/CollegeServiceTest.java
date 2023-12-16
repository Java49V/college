package telran.college;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import telran.college.dto.*;
import telran.college.entities.Lecturer;
import telran.college.entities.Subject;
import telran.college.service.CollegeService;
import telran.exceptions.NotFoundException;

@SpringBootTest
@Sql(scripts = { "db_test.sql" })
class CollegeServiceTest {
	@Autowired
	CollegeService collegeService;

	@Test
	void bestStudentsTypeTest() {
		List<String> students = collegeService.bestStudentsSubjectType(SubjectType.BACK_END, 2);
		String[] expected = { "David", "Yosef" };
		assertArrayEquals(expected, students.toArray(String[]::new));
	}

	@Test
	void studentsAvgScoreTest() {
		List<NameScore> studentMarks = collegeService.studentsAvgMarks();
		String[] students = { "David", "Rivka", "Vasya", "Sara", "Yosef" };
		int[] scores = { 96, 95, 83, 80, 78 };
		NameScore[] studentMarksArr = studentMarks.toArray(NameScore[]::new);

		IntStream.range(0, students.length).forEach(i -> {
			assertEquals(students[i], studentMarksArr[i].getStudentName());
			assertEquals(scores[i], studentMarksArr[i].getScore());
		});
	}

	@Test
	void lecturersMostHoursTest() {
		List<LecturerHours> lecturersHours = collegeService.lecturersMostHours(2);
		LecturerHours[] lecturersHoursArr = lecturersHours.toArray(LecturerHours[]::new);
		String[] lecturers = { "Abraham", "Mozes" };
		int[] hours = { 225, 130 };
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
		assertEquals("Rehovot", studentCity.getStudentCity());
		assertEquals("Yakob", studentCity.getStudentName());
	}

	@Test
	void studentsBurnMonthTest() {
		String[] namesExpected = { "Vasya", "Yakob" };
		String[] phonesExpected = { "054-1234567", "051-6677889" };
		NamePhone[] studentPhonesArr = collegeService.studentsBurnMonth(10).toArray(NamePhone[]::new);
		assertEquals(phonesExpected.length, studentPhonesArr.length);
		IntStream.range(0, phonesExpected.length).forEach(i -> {
			assertEquals(namesExpected[i], studentPhonesArr[i].getName());
			assertEquals(phonesExpected[i], studentPhonesArr[i].getPhone());
		});
	}

	@Test
	void lecturesCityTest() {
		String[] expectedNames = { "Abraham", "Mozes" };
		String[] expectedPhones = { "050-1111122", "054-3334567" };
		NamePhone[] namePhones = collegeService.lecturersCity("Jerusalem").toArray(NamePhone[]::new);
		assertEquals(expectedNames.length, namePhones.length);
		IntStream.range(0, namePhones.length).forEach(i -> {
			assertEquals(expectedNames[i], namePhones[i].getName());
			assertEquals(expectedPhones[i], namePhones[i].getPhone());
		});
	}

	@Test
	void subjectsScoresTest() {
		String[] subjects = { "Java Core", "Java Technologies", "HTML/CSS", "JavaScript", "React" };
		int[] scores = { 75, 60, 95, 85, 100 };
		SubjectNameScore[] subjectScores = collegeService.subjectsScores("Vasya").toArray(SubjectNameScore[]::new);
		assertEquals(scores.length, subjectScores.length);
		IntStream.range(0, scores.length).forEach(i -> {
			assertEquals(subjects[i], subjectScores[i].getSubjectName());
			assertEquals(scores[i], subjectScores[i].getScore());
		});

	}

	@Test
	public void testAddLecturer() {
		PersonDto personDto = new PersonDto(1234L, "John Doe", LocalDate.now(), "City", "Phone");
		when(lecturerRepo.existsById(anyLong())).thenReturn(false);
		when(lecturerRepo.save(any(Lecturer.class))).thenReturn(new Lecturer(personDto));

		PersonDto result = collegeService.addLecturer(personDto);

		assertEquals(personDto, result);
		verify(lecturerRepo, times(1)).existsById(anyLong());
		verify(lecturerRepo, times(1)).save(any(Lecturer.class));
	}

	@Test
	public void addLecturer_NormalCase() {
		PersonDto personDto = new PersonDto(1234L, "John Doe", LocalDate.now(), "City", "Phone");
		when(lecturerRepo.existsById(anyLong())).thenReturn(false);
		when(lecturerRepo.save(any(Lecturer.class))).thenReturn(new Lecturer(personDto));

		PersonDto result = collegeService.addLecturer(personDto);

		assertEquals(personDto, result);
		verify(lecturerRepo, times(1)).existsById(anyLong());
		verify(lecturerRepo, times(1)).save(any(Lecturer.class));
	}

	@Test(expected = IllegalStateException.class)
	public void addLecturer_LecturerExists() {
		PersonDto personDto = new PersonDto(1234L, "John Doe", LocalDate.now(), "City", "Phone");
		when(lecturerRepo.existsById(anyLong())).thenReturn(true);

		collegeService.addLecturer(personDto);
	}

	@Test
	public void addMark_NormalCase() {
	    MarkDto markDto = new MarkDto(123L, 321L, 85);
	    Student student = new Student(new PersonDto(...)); // Fill in details
	    Subject subject = new Subject(new SubjectDto(...)); // Fill in details

	    when(studentRepo.findById(anyLong())).thenReturn(Optional.of(student));
	    when(subjectRepo.findById(anyLong())).thenReturn(Optional.of(subject));
	    when(markRepo.save(any(Mark.class))).thenReturn(new Mark(student, subject, 85));

	    MarkDto result = collegeService.addMark(markDto);

	    assertEquals(markDto, result);
	    verify(studentRepo, times(1)).findById(anyLong());
	    verify(subjectRepo, times(1)).findById(anyLong());
	    verify(markRepo, times(1)).save(any(Mark.class));
	}

	@Test(expected = NotFoundException.class)
	public void addMark_StudentNotFound() {
		MarkDto markDto = new MarkDto(123L, 321L, 85);
		when(studentRepo.findById(anyLong())).thenReturn(Optional.empty());

		collegeService.addMark(markDto);
	}

	@Test(expected = NotFoundException.class)
	public void addMark_SubjectNotFound() {
	    MarkDto markDto = new MarkDto(123L, 321L, 85);
	    when(studentRepo.findById(anyLong())).thenReturn(Optional.of(new Student(...)));
	    when(subjectRepo.findById(anyLong())).thenReturn(Optional.empty());

	    collegeService.addMark(markDto);
	}

	@Test
	public void updateLecturer_NormalCase() {
	    PersonDto personDto = new PersonDto(1234L, "John Doe", LocalDate.now(), "New City", "New Phone");
	    Lecturer lecturer = new Lecturer(new PersonDto(...));

	    when(lecturerRepo.findById(anyLong())).thenReturn(Optional.of(lecturer));

	    PersonDto result = collegeService.updateLecturer(personDto);

	    assertEquals(personDto, result);
	    verify(lecturerRepo, times(1)).findById(anyLong());
	}

	@Test(expected = NotFoundException.class)
	public void updateLecturer_NotFound() {
		PersonDto personDto = new PersonDto(1234L, "John Doe", LocalDate.now(), "New City", "New Phone");
		when(lecturerRepo.findById(anyLong())).thenReturn(Optional.empty());

		collegeService.updateLecturer(personDto);
	}

	@Test
	public void deleteLecturer_NormalCase() {
	    long lecturerId = 1234L;
	    Lecturer lecturer = new Lecturer(new PersonDto(...));
	    List<Subject> subjects = Arrays.asList(new Subject(...), new Subject(...)); // Fill in details

	    when(lecturerRepo.findById(lecturerId)).thenReturn(Optional.of(lecturer));
	    when(subjectRepo.findByLecturer(lecturer)).thenReturn(subjects);

	    PersonDto result = collegeService.deleteLecturer(lecturerId);

	    assertEquals(lecturer.build(), result);
	    verify(lecturerRepo, times(1)).delete(lecturer);
	    verify(subjectRepo, times(1)).findByLecturer(lecturer);
	    subjects.forEach(subject -> assertNull(subject.getLecturer()));
	}

	@Test(expected = NotFoundException.class)
	public void deleteLecturer_NotFound() {
		long lecturerId = 1234L;
		when(lecturerRepo.findById(lecturerId)).thenReturn(Optional.empty());

		collegeService.deleteLecturer(lecturerId);
	}
//
//	@Test
//	public void deleteSubject_NormalCase() {
//	    long subjectId = 321L;
//	    Subject subject = new Subject(new SubjectDto(...)); // Fill in details
//
//	    when(subjectRepo.findById(subjectId)).thenReturn(Optional.of(subject));
//
//	    SubjectDto result = collegeService.deleteSubject(subjectId);
//
//	    assertEquals(subject.build(), result);
//	    verify(markRepo, times(1)).deleteBySubject(subject);
//	    verify(subjectRepo, times(1)).delete(subject);
//	}

	@Test(expected = NotFoundException.class)
	public void deleteSubject_NotFound() {
		long subjectId = 321L;
		when(subjectRepo.findById(subjectId)).thenReturn(Optional.empty());

		collegeService.deleteSubject(subjectId);
	}

//	@Test
//	public void deleteStudentsHavingScoresLess_NormalCase() {
//	    int nScores = 3;
//	    List<Student> students = Arrays.asList(new Student(new PersonDto(...)), new Student(new PersonDto(...)));
//
//	    when(studentRepo.findStudentsWithLessThanNScores(nScores)).thenReturn(students);
//
//	    List<PersonDto> result = collegeService.deleteStudentsHavingScoresLess(nScores);
//
//	    assertEquals(students.stream().map(Student::build).collect(Collectors.toList()), result);
//	    verify(studentRepo, times(1)).deleteAll(students);
//	}

}
