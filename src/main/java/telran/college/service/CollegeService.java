package telran.college.service;

import java.util.List;
import java.util.stream.Collectors;

import telran.college.dto.StudentMark;
import telran.college.entities.Lecturer;
import telran.college.entities.Student;

public interface CollegeService {
	List<String> bestStudentsSubjectType(String type, int nStudents);

	List<StudentMark> studentsAvgMarks();
	
	public List<Lecturer> findTopLecturers(int count) {
	    return lecturerRepo.findTopLecturersWithMostHours(PageRequest.of(0, count));
	}
	
	public List<Student> findStudentsWithLessThanTotalScore(long scoreThreshold) {
	    return studentRepo.findStudentsWithLessThanTotalScore(scoreThreshold);
	}
	
	public List<Student> findStudentsBornInMonth(int month) {
	    return studentRepo.findAll().stream()
	        .filter(s -> s.getBirthDate().getMonthValue() == month)
	        .collect(Collectors.toList());
	}
	
	public Map<String, Integer> getStudentSubjectsAndScores(String studentName) {
	    return markRepo.findByStudentName(studentName).stream()
	        .collect(Collectors.toMap(mark -> mark.getSubject().getName(), Mark::getScore));
	}
	
	
	public List<Lecturer> getLecturersFromCity(String city) {
	    return lecturerRepo.findAll().stream()
	        .filter(l -> city.equals(l.getCity()))
	        .collect(Collectors.toList());
	}





}


