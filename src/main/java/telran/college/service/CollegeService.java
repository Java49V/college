package telran.college.service;

import java.util.List;

import telran.college.dto.StudentMark;

public interface CollegeService {
	List<String> bestStudentsSubjectType(String type, int nStudents);

	List<StudentMark> studentsAvgMarks();
}
