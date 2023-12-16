package telran.college.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.college.dto.*;
import telran.college.entities.*;
import telran.college.repo.*;
import telran.exceptions.NotFoundException;
@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class CollegeServiceImpl implements CollegeService {
	final StudentRepo studentRepo;
	final LecturerRepo lecturerRepo;
	final SubjectRepo subjectRepo;
	final MarkRepo markRepo;
	@Override
	public List<String> bestStudentsSubjectType(SubjectType type, int nStudents) {
		
		return studentRepo.findBestStudentsSubjectType( type, nStudents);
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
		
		return lecturerRepo.findByCity(city);
	}
	@Override
	public List<SubjectNameScore> subjectsScores(String studentName) {
		
		return markRepo.findByStudentName(studentName);
	}
	@Override
	@Transactional(readOnly = false)
	public PersonDto addStudent(PersonDto personDto) {
		if(studentRepo.existsById(personDto.id())) {
			throw new IllegalStateException(personDto.id() + " already exists");
		}
		studentRepo.save(new Student(personDto));
		return personDto;
	}
	@Override
	@Transactional(readOnly = false)
	public PersonDto addLecturer(PersonDto personDto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@Transactional(readOnly = false)
	public SubjectDto addSubject(SubjectDto subjectDto) {
		Lecturer lecturer = null;
		if(subjectDto.lecturerId() != null) {
			lecturer = lecturerRepo.findById(subjectDto.lecturerId())
					.orElseThrow(() -> new NotFoundException(subjectDto.lecturerId() + "not exists"));
		}
		Subject subject = new Subject(subjectDto);
		subject.setLecturer(lecturer);
		subjectRepo.save(subject);
		return subjectDto;
	}
	
	@Override
	@Transactional(readOnly = false)
	public MarkDto addMark(MarkDto markDto) {
	    Student student = studentRepo.findById(markDto.studentId())
	        .orElseThrow(() -> new NotFoundException("Student not found"));
	    Subject subject = subjectRepo.findById(markDto.subjectId())
	        .orElseThrow(() -> new NotFoundException("Subject not found"));
	    Mark mark = new Mark(student, subject, markDto.score());
	    markRepo.save(mark);
	    return markDto;
	}
	
	@Override
	@Transactional(readOnly = false)
	public PersonDto updateStudent(PersonDto personDto) {
		Student student = studentRepo.findById(personDto.id())
				.orElseThrow(() -> new NotFoundException(personDto.id() + " not exists"));
		student.setCity(personDto.city());
		student.setPhone(personDto.phone());
		return personDto;
	}
	
	@Override
	@Transactional(readOnly = false)
	public PersonDto updateLecturer(PersonDto personDto) {
	    Lecturer lecturer = lecturerRepo.findById(personDto.id())
	        .orElseThrow(() -> new NotFoundException("Lecturer not found"));
	    lecturer.setCity(personDto.city());
	    lecturer.setPhone(personDto.phone());
	    return personDto;
	}
	
	@Override
	@Transactional(readOnly = false)
	public PersonDto deleteLecturer(long id) {
	    Lecturer lecturer = lecturerRepo.findById(id)
	        .orElseThrow(() -> new NotFoundException("Lecturer not found"));
	    subjectRepo.findByLecturer(lecturer).forEach(subject -> subject.setLecturer(null));
	    lecturerRepo.delete(lecturer);
	    return lecturer.build();
	}

	@Override
	@Transactional(readOnly = false)
	public SubjectDto deleteSubject(long id) {
	    Subject subject = subjectRepo.findById(id)
	        .orElseThrow(() -> new NotFoundException("Subject not found"));
	    markRepo.deleteBySubject(subject);
	    subjectRepo.delete(subject);
	    return subject.build();
	}

	
	@Override
	@Transactional(readOnly = false)
	public List<PersonDto> deleteStudentsHavingScoresLess(int nScores) {
	    List<Student> studentsToDelete = studentRepo.findStudentsWithLessThanNScores(nScores);
	    studentRepo.deleteAll(studentsToDelete);
	    return studentsToDelete.stream().map(Student::build).collect(Collectors.toList());
	}

}
