package telran.college.service;

import java.util.List;

import telran.college.dto.*;

public interface CollegeService {
	List<String> bestStudentsSubjectType(String type, int nStudents);
	List<NameScore> studentsAvgMarks();
	List<LecturerHours> lecturersMostHours(int nLecturers);
	List<StudentCity> studentsScoresLess(int nThreshold);
	List<NamePhone> studentsBurnMonth(int month);
	List<NamePhone> lecturersCity(String city);
	List<NameScore> subjectsScores(String studentName);
	
}
