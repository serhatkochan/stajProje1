package mahrek.stajProje1.business.abstracts;

import java.util.List;

import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.Result;
import mahrek.stajProje1.entities.concretes.Student;

public interface StudentService {
	
	DataResult<List<Student>> getAll();
	
	DataResult<Student> add(Student student);
	
	DataResult<List<Student>> getAll(int pageNo, int pageSize);
	
	DataResult<Student> findByStudentNo(String studentNo);
	
	DataResult<Student> deleteById(int studentId);
	
	DataResult<Student> findById(int studentId);
	
	DataResult<Student> update(Student student);
}
