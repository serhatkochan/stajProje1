package mahrek.stajProje1.core.dataAccess;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mahrek.stajProje1.core.entities.Person;
import mahrek.stajProje1.core.entities.Province;
import mahrek.stajProje1.core.entities.dtos.PersonAddDto;
import mahrek.stajProje1.core.entities.dtos.PersonDto;
import mahrek.stajProje1.core.entities.dtos.PersonUpdateDto;

public interface PersonDao extends JpaRepository<Person, Integer> {

	@Query("Select new mahrek.stajProje1.core.entities.dtos.PersonDto"
			+ "(p.personId, p.nationalityId, p.firstName, p.lastName, p.dateOfBirth, d.districtId, d.province.provinceName, d.districtName)"
			+ " From Person p Inner Join p.district d where p.personId = ?1")
	PersonDto findById(int personId); // id'ye ait kullanıcıyı getirir
	
	Person getByPersonId(int personId);
	
	@Query("Select new mahrek.stajProje1.core.entities.dtos.PersonDto"
			+ "(p.personId, p.nationalityId, p.firstName, p.lastName, p.dateOfBirth, d.districtId, d.province.provinceName, d.districtName)"
			+ " From Person p Inner Join p.district d where p.nationalityId = ?1")
	PersonDto findByNationalityId(String nationalityId);
	
	boolean existsByNationalityId(String nationalityId);
	
	boolean existsById(int personId);
	
	@Query("Select new mahrek.stajProje1.core.entities.dtos.PersonDto"
			+ "(p.personId, p.nationalityId, p.firstName, p.lastName, p.dateOfBirth, d.districtId, d.province.provinceName, d.districtName)"
			+ " From Person p Inner Join p.district d "
			+ "where "
			+ "p.nationalityId = :#{#personAddDto.nationalityId}")
	PersonDto findByPerson(@Param("personAddDto") PersonAddDto personAddDto); // NationalityId'ye ait olan kullanıcıyı getirir
	
	@Query("Select new mahrek.stajProje1.core.entities.dtos.PersonDto"
			+ "(p.personId, p.nationalityId, p.firstName,"
			+ " p.lastName, p.dateOfBirth, d.districtId, d.province.provinceName, d.districtName)"
			+ " From Person p Inner Join p.district d "
			+ "where "
			+ "p.nationalityId = :#{#personDto.nationalityId}")
	PersonDto findByPerson(@Param("personDto") PersonUpdateDto personUpdateDto); // nationalityId'ye ait olan kullanıcıyı getirir
	
	Person findByFirstNameAndLastNameAndDateOfBirthAndDistrict_DistrictId(String firstName, String lastName, Date dateOfBirth, int districtId);
	
	boolean existsByFirstNameAndLastNameAndDateOfBirthAndDistrict_DistrictId(String firstName, String lastName, Date dateOfBirth, int districtId);
	
	PersonDto deleteById(int personId);
	
	@Query("Select new mahrek.stajProje1.core.entities.dtos.PersonDto"
			+ "(p.personId, p.nationalityId, p.firstName, p.lastName, p.dateOfBirth, d.districtId, d.province.provinceName, d.districtName)"
			+ " From Person p Inner Join p.district d")
	List<PersonDto> findAllPerson(); // bütün personları getirir
	
	@Query("Select new mahrek.stajProje1.core.entities.dtos.PersonDto"
			+ "(p.personId, p.nationalityId, p.firstName, p.lastName, p.dateOfBirth, d.districtId, d.province.provinceName, d.districtName)"
			+ " From Person p Inner Join p.district d")
	Page<PersonDto> findAllPerson(Pageable pageable); // personları sayfalar
}
