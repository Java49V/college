package telran.college.service;

import java.util.List;

import telran.college.dto.*;

public interface CollegeService {
	List<String> bestStudentsSubjectType(SubjectType type, int nStudents);
	List<NameScore> studentsAvgMarks();
	List<LecturerHours> lecturersMostHours(int nLecturers);
	List<StudentCity> studentsScoresLess(int nThreshold);
	List<NamePhone> studentsBurnMonth(int month);
	List<NamePhone> lecturersCity(String city);
	List<SubjectNameScore> subjectsScores(String studentName);
	PersonDto addStudent(PersonDto personDto);
	PersonDto addLecturer(PersonDto personDto);
	SubjectDto addSubject(SubjectDto subjectDto);
	MarkDto addMark(MarkDto markDto);
	PersonDto updateStudent(PersonDto personDto);
	PersonDto updateLecturer(PersonDto personDto);
	PersonDto deleteLecturer(long id);
	SubjectDto deleteSubject(long id);
	List<PersonDto> deleteStudentsHavingScoresLess(int nScores);
	
}
