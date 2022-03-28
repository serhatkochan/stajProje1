package mahrek.stajProje1.business.concretes;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mahrek.stajProje1.business.abstracts.PersonService;
import mahrek.stajProje1.business.abstracts.StudentService;
import mahrek.stajProje1.core.dataAccess.PersonDao;
import mahrek.stajProje1.core.entities.Person;
import mahrek.stajProje1.core.entities.User;
import mahrek.stajProje1.core.entities.dtos.PersonDto;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.ErrorDataResult;
import mahrek.stajProje1.core.utilities.results.ErrorResult;
import mahrek.stajProje1.core.utilities.results.Result;
import mahrek.stajProje1.core.utilities.results.SuccessDataResult;
import mahrek.stajProje1.core.utilities.results.SuccessResult;
import mahrek.stajProje1.dataAccess.abstracts.StudentDao;
import mahrek.stajProje1.entities.concretes.Student;
import mahrek.stajProje1.entities.concretes.dtos.StudentAddDto;
import mahrek.stajProje1.entities.concretes.dtos.StudentDto;
import mahrek.stajProje1.entities.concretes.dtos.StudentUpdateDto;

@Service // servis
public class StudentManager implements StudentService {

	private StudentDao studentDao; // injection
	private PersonService personService;
	private PersonDao personDao;

	@Autowired
	public StudentManager(StudentDao studentDao, PersonService personService, PersonDao personDao) {
		this.studentDao = studentDao;
		this.personService = personService;
		this.personDao = personDao;
	}

	@Override
	public DataResult<List<StudentDto>> getAll() {
		return new SuccessDataResult<List<StudentDto>>(studentDao.findAllStudent(), "Student Listelendi");
	}

	@Override
	public DataResult<StudentDto> add(StudentAddDto studentAddDto) {
		if (studentDao.existsByStudentNo(studentAddDto.getStudentNo())) {
			return new ErrorDataResult<StudentDto>("Student No Kullanımda.");
		} else if (studentDao.existsByPerson_PersonId(studentAddDto.getPersonId())) {
			return new ErrorDataResult<StudentDto>("Bu Person Zaten Student");
		} else {
			Student student = new Student();
			student.setStudentNo(studentAddDto.getStudentNo());
			student.setPerson(personDao.getByPersonId(studentAddDto.getPersonId()));
			studentDao.save(student);
			return new SuccessDataResult<StudentDto>("Başarılı");
		}

	}

	@Override
	public DataResult<List<StudentDto>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return new SuccessDataResult<List<StudentDto>>(this.studentDao.findAllStudent(pageable).getContent(),
				"Student Listelendi");
	}

	@Override
	public DataResult<Student> findByStudentNo(String studentNo) {
		if (this.studentDao.existsByStudentNo(studentNo)) {
			return new SuccessDataResult<Student>("this.studentDao.findByStudentNo(studentNo)");
		} else {
			return new ErrorDataResult<Student>("Student No bulunamadı.");
		}
	}

	@Override
	public DataResult<Student> deleteById(int studentId) {
		return new SuccessDataResult<Student>(this.studentDao.deleteById(studentId), "Student silindi");
	}

	@Override
	public DataResult<StudentDto> findById(int studentId) {
		return new SuccessDataResult<StudentDto>(studentDao.findByStudentId(studentId), "Student getirildi.");
	}

	@Override
	public DataResult<StudentDto> update(StudentUpdateDto studentUpdateDto) {
		if (studentDao.findByStudentId(studentUpdateDto.getStudentId()).getPersonId() != studentUpdateDto
				.getPersonId()) {
			// person idler farklı
			if (studentDao.findByPersonId(studentUpdateDto.getPersonId()) != null) {
				return new ErrorDataResult<StudentDto>("Bu Person zaten Student");
			}
		} else if (studentDao.findByStudentNo(studentUpdateDto.getStudentNo()) != null) {
			if (studentDao.findByStudentNo(studentUpdateDto.getStudentNo()).getStudentId()
					!= studentUpdateDto.getStudentId()) {
				// student No kullanımda mı
				return new ErrorDataResult<StudentDto>("Bu Student No Kullanımda");
			}
		}
		Student student = new Student();
		student.setStudentId(studentUpdateDto.getStudentId());
		student.setStudentNo(studentUpdateDto.getStudentNo());
		student.setPerson(personDao.getByPersonId(studentUpdateDto.getPersonId()));
		studentDao.save(student);
		return new SuccessDataResult<StudentDto>("Student Güncellendi");

	}

}
