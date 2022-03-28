package mahrek.stajProje1.business.abstracts;

import java.util.List;

import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.Result;
import mahrek.stajProje1.entities.concretes.Student;
import mahrek.stajProje1.entities.concretes.dtos.StudentAddDto;
import mahrek.stajProje1.entities.concretes.dtos.StudentDto;
import mahrek.stajProje1.entities.concretes.dtos.StudentUpdateDto;

public interface StudentService {
	
	DataResult<List<StudentDto>> getAll();
	
	DataResult<StudentDto> add(StudentAddDto studentAddDto);
	
	DataResult<List<StudentDto>> getAll(int pageNo, int pageSize);
	
	DataResult<Student> findByStudentNo(String studentNo);
	
	DataResult<Student> deleteById(int studentId);
	
	DataResult<StudentDto> findById(int studentId);
	
	DataResult<StudentDto> update(StudentUpdateDto studentUpdateDto);
}
