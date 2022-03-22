package mahrek.stajProje1.dataAccess.abstracts;


import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mahrek.stajProje1.core.entities.Person;
import mahrek.stajProje1.entities.concretes.Student;

public interface StudentDao extends JpaRepository<Student, Integer>{
	
	Student findById(int id);

	boolean existsById(int id);
	
	boolean existsByStudentNo(String studentNo);
	
	Student findByStudentNo(String studentNo);
	
//	@Query("From Student where person.personId=:personId") 
	boolean existsByPerson_PersonId(int personId);
	
	Student deleteById(int studentId);
}
