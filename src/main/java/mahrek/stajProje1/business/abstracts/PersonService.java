package mahrek.stajProje1.business.abstracts;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import mahrek.stajProje1.core.entities.Person;
import mahrek.stajProje1.core.entities.Province;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.Result;

public interface PersonService {

	DataResult<Person> add(Person person);
	
	DataResult<List<Person>> getAll();
	
	DataResult<Person> findByFirstNameAndLastNameAndDateOfBirthAndProvince_ProvinceName(String firstName, String lastName, Date dateOfBirth, String provinceName);
	
	DataResult<Person> findById(int personId);
	
	DataResult<Person> deleteById(int personId);
	
	DataResult<Person> update(Person person);
	
	DataResult<List<Person>> getAll(int pageNo, int pageSize);
	
}
