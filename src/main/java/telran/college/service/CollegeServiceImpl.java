package telran.college.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.college.dto.StudentMark;
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
	public List<StudentMark> studentsAvgMarks() {
		
		return studentRepo.studentsMarks();
	}

}
