package mahrek.stajProje1.dataAccess.abstracts;


import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mahrek.stajProje1.entities.concretes.Student;
import mahrek.stajProje1.entities.concretes.dtos.StudentDto;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends JpaRepository<Student, Integer>{


	List<Student> findAll();

	@Query("Select new mahrek.stajProje1.entities.concretes.dtos.StudentDto"
			+ "(s.studentId, s.studentNo,"
			+ " p.personId, p.nationalityId, p.firstName, p.lastName, p.dateOfBirth,"
			+ " p.district.province.provinceName, p.district.districtId, p.district.districtName)"
			+ " From Student s Inner Join s.person p"
			+ " where"
			+ " s.studentId = :studentId")
	StudentDto findByStudentId(int studentId);

	boolean existsById(int id);
	
	boolean existsByStudentNo(String studentNo);
	
	@Query("Select new mahrek.stajProje1.entities.concretes.dtos.StudentDto"
			+ "(s.studentId, s.studentNo,"
			+ " p.personId, p.nationalityId, p.firstName, p.lastName, p.dateOfBirth,"
			+ " p.district.province.provinceName, p.district.districtId, p.district.districtName)"
			+ " From Student s Inner Join s.person p"
			+ " where"
			+ " s.studentNo = ?1")
	StudentDto findByStudentNo(String studentNo);
	
	boolean existsByPerson_PersonId(int personId);
	
	Student findByPerson_PersonId(int personId);
	
	Student deleteById(int studentId);
	
	@Query("Select new mahrek.stajProje1.entities.concretes.dtos.StudentDto"
			+ "(s.studentId, s.studentNo,"
			+ " p.personId, p.nationalityId, p.firstName, p.lastName, p.dateOfBirth,"
			+ " p.district.province.provinceName, p.district.districtId, p.district.districtName)"
			+ " From Student s Inner Join s.person p"
			+ " where p.personId = ?1")
	StudentDto findByPersonId(int personId);
	
	@Query("Select new mahrek.stajProje1.entities.concretes.dtos.StudentDto"
			+ "(s.studentId, s.studentNo,"
			+ " p.personId, p.nationalityId, p.firstName, p.lastName, p.dateOfBirth,"
			+ " p.district.province.provinceName, p.district.districtId, p.district.districtName)"
			+ " From Student s Inner Join s.person p")
	List<StudentDto> findAllStudent();
	
	@Query("Select new mahrek.stajProje1.entities.concretes.dtos.StudentDto"
			+ "(s.studentId, s.studentNo,"
			+ " p.personId, p.nationalityId, p.firstName, p.lastName, p.dateOfBirth,"
			+ " p.district.province.provinceName, p.district.districtId, p.district.districtName)"
			+ " From Student s Inner Join s.person p")
	Page<StudentDto> findAllStudent(Pageable pageable); // personları sayfalar
}
