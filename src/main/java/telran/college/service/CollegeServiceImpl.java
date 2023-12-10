package telran.college.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.college.dto.LecturerHours;
import telran.college.dto.StudentCity;
import telran.college.dto.NameScore;
import telran.college.dto.NamePhone;
import telran.college.repo.*;
@Service
@RequiredArgsConstructor
public class CollegeServiceImpl implements CollegeService {
	final StudentRepo studentRepo;
	final LecturerRepo lecturerRepo;
	final SubjectRepo subjectRepo;
	final MarkRepo markRepo;
	@Override
	public List<String> bestStudentsSubjectType(String type, int nStudents) {
		
		return studentRepo.findBestStudentsSubjectType(type, nStudents);
	}
	@Override
	public List<NameScore> studentsAvgMarks() {
		
		return studentRepo.studentsMarks();
	}
	@Override
	public List<LecturerHours> lecturersMostHours(int nLecturers) {
		
		return lecturerRepo.findLecturersMostHours(nLecturers);
	}
	@Override
	public List<StudentCity> studentsScoresLess(int nThreshold) {
		
		return studentRepo.findStudentsScoresLess(nThreshold);
	}
	@Override
	public List<NamePhone> studentsBurnMonth(int month) {
		
		return studentRepo.findStudentsBurnMonth(month);
	}
	@Override
	public List<NamePhone> lecturersCity(String city) {
		
		return lecturerRepo.findLecturersCity(city);
	}
	@Override
	public List<NameScore> subjectsScores(String studentName) {
		
		return studentRepo.findSubjectScore(studentName);
	}

}
