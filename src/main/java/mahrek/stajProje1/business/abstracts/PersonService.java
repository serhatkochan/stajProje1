package mahrek.stajProje1.business.abstracts;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import mahrek.stajProje1.core.entities.Person;
import mahrek.stajProje1.core.entities.Province;
import mahrek.stajProje1.core.entities.dtos.PersonAddDto;
import mahrek.stajProje1.core.entities.dtos.PersonDto;
import mahrek.stajProje1.core.entities.dtos.PersonUpdateDto;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.Result;

public interface PersonService {

	DataResult<PersonDto> add(PersonAddDto personAddDto);
	
	DataResult<List<PersonDto>> getAll();
	
	DataResult<PersonDto> findByFirstNameAndLastNameAndDateOfBirthAndProvince_ProvinceName(String firstName, String lastName, Date dateOfBirth, String provinceName);
	
	DataResult<PersonDto> findById(int personId);
	
	DataResult<PersonDto> deleteById(int personId);
	
	DataResult<PersonDto> update(PersonUpdateDto personUpdateDto);
	
	DataResult<List<PersonDto>> getAll(int pageNo, int pageSize);
	
}
