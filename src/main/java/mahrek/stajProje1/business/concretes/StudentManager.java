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
import mahrek.stajProje1.core.entities.Person;
import mahrek.stajProje1.core.entities.User;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.ErrorDataResult;
import mahrek.stajProje1.core.utilities.results.ErrorResult;
import mahrek.stajProje1.core.utilities.results.Result;
import mahrek.stajProje1.core.utilities.results.SuccessDataResult;
import mahrek.stajProje1.core.utilities.results.SuccessResult;
import mahrek.stajProje1.dataAccess.abstracts.StudentDao;
import mahrek.stajProje1.entities.concretes.Student;

@Service // servis
public class StudentManager implements StudentService {
	
	private StudentDao studentDao; // injection
	private PersonService personService;
	
	@Autowired
	public StudentManager(StudentDao studentDao, PersonService personService) {
		this.studentDao = studentDao;
		this.personService = personService;
	}

	@Override
	public DataResult<List<Student>> getAll() {
		return new SuccessDataResult<List<Student>>(this.studentDao.findAll(), "Student Listelendi");
	}

	@Override
	public DataResult<Student> add(Student student) {
		if(this.findByStudentNo(student.getStudentNo()).isSuccess()) {
			return new ErrorDataResult<Student>(this.findByStudentNo(student.getStudentNo()).getData(), "Student No Kullanımda.");
		}
		else {
			if(this.personService.findByFirstNameAndLastNameAndDateOfBirthAndProvince_ProvinceName(
					student.getPerson().getFirstName(), student.getPerson().getLastName()
					, student.getPerson().getDateOfBirth(), student.getPerson().getProvince().getProvinceName()).isSuccess()) {
				
				student.setPerson(this.personService.findByFirstNameAndLastNameAndDateOfBirthAndProvince_ProvinceName(
					student.getPerson().getFirstName(), student.getPerson().getLastName()
					, student.getPerson().getDateOfBirth(), student.getPerson().getProvince().getProvinceName()).getData());
				
				if(this.studentDao.existsByPerson_PersonId(student.getPerson().getPersonId())) {
					return new ErrorDataResult<Student>(student, "Bu person zaten student");
				}
			}
			else {
				this.personService.add(student.getPerson());
			}
			this.studentDao.save(student);
			return new SuccessDataResult<Student>(student, "Student eklendi");
		}
//		return new SuccessDataResult<Student>(
//				"\n getStudentId:" + student.getStudentId()
//				+"\n getStudentNo:" + student.getStudentNo()
//				+"\n getFirstName():" + student.getPerson().getFirstName()
//				+"\n getLastName():" + student.getPerson().getLastName()
//				+"\n getDateOfBirth()" + student.getPerson().getDateOfBirth()
//				+"\n getPersonId():" + student.getPerson().getPersonId()
//				+"\n getProvinceName():" + student.getPerson().getProvince().getProvinceName()
//				+"\n getProvinceId(): " + student.getPerson().getProvince().getProvinceId()
//				); 
		
	}
	
	@Override
	public DataResult<List<Student>> getAll(int pageNo, int pageSize){
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return new SuccessDataResult<List<Student>>(this.studentDao.findAll(pageable).getContent(), "Student Listelendi");
	}

	@Override
	public DataResult<Student> findByStudentNo(String studentNo) {
		if(this.studentDao.existsByStudentNo(studentNo)) {
			return new SuccessDataResult<Student>(this.studentDao.findByStudentNo(studentNo));
		}
		else {
			return new ErrorDataResult<Student>("Student No bulunamadı.");
		}
	}

	@Override
	public DataResult<Student> deleteById(int studentId) {
		return new SuccessDataResult<Student>(this.studentDao.deleteById(studentId), "Student silindi");
	}

	@Override
	public DataResult<Student> findById(int studentId) {
		return new SuccessDataResult<Student>(this.studentDao.findById(studentId), "Student getirildi.");
	}

	@Override
	public DataResult<Student> update(Student student) {
		// farklı bir studentNo girilmek isteniyor ise
		if(!this.findById(student.getStudentId()).getData().getStudentNo().equals(student.getStudentNo())) {
			// student no kullanılıyor mu
			if (this.findByStudentNo(student.getStudentNo()).isSuccess()){
				return new ErrorDataResult<Student>("Student No Kullanımda.");
			}
		}
		// eğer person bilgisi degistirilmek isteniyor ise
		if(this.findById(student.getStudentId()).getData().getPerson().getPersonId() != student.getPerson().getPersonId()) {
			// secilen person zaten student mi
			if(this.studentDao.existsByPerson_PersonId(student.getPerson().getPersonId())) {
				return new ErrorDataResult<Student>(student, "Bu person zaten student");
			}
			else {
				student.setPerson(this.personService.findById(student.getPerson().getPersonId()).getData());
			}
		}
		this.studentDao.save(student);
		return new SuccessDataResult<Student>(student, "Student güncellendi");
		
	}
	
	
}
