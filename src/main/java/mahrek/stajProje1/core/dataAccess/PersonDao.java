package mahrek.stajProje1.core.dataAccess;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import mahrek.stajProje1.core.entities.Person;
import mahrek.stajProje1.core.entities.Province;

public interface PersonDao extends JpaRepository<Person, Integer> {

	Person findById(int personId);
	
	boolean existsById(int personId);
	
	Person findByFirstNameAndLastNameAndDateOfBirthAndProvince_ProvinceName(String firstName, String lastName, Date dateOfBirth, String provinceName);
	
	boolean existsByFirstNameAndLastNameAndDateOfBirthAndProvince_ProvinceName(String firstName, String lastName, Date dateOfBirth, String provinceName);
	
	Person deleteById(int personId);
	
	
}
