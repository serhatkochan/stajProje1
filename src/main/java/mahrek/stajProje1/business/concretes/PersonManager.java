package mahrek.stajProje1.business.concretes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mahrek.stajProje1.business.abstracts.DistrictService;
import mahrek.stajProje1.business.abstracts.PersonService;
import mahrek.stajProje1.core.dataAccess.PersonDao;
import mahrek.stajProje1.core.dataAccess.ProvinceDao;
import mahrek.stajProje1.core.entities.District;
import mahrek.stajProje1.core.entities.Person;
import mahrek.stajProje1.core.entities.Province;
import mahrek.stajProje1.core.entities.dtos.PersonAddDto;
import mahrek.stajProje1.core.entities.dtos.PersonDto;
import mahrek.stajProje1.core.entities.dtos.PersonUpdateDto;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.ErrorDataResult;
import mahrek.stajProje1.core.utilities.results.Result;
import mahrek.stajProje1.core.utilities.results.SuccessDataResult;
import mahrek.stajProje1.core.utilities.results.SuccessResult;
import mahrek.stajProje1.entities.concretes.Student;
import springfox.documentation.swagger2.mappers.ModelMapper;

@Service
public class PersonManager implements PersonService {

	private PersonDao personDao;
	private ProvinceDao provinceDao;
	private DistrictService districtService;

	@Autowired
	public PersonManager(PersonDao personDao, ProvinceDao provinceDao, DistrictService districtService) {
		this.personDao = personDao;
		this.provinceDao = provinceDao;
		this.districtService = districtService;
	}

	@SuppressWarnings("deprecation")
	@Override
	public DataResult<PersonDto> add(PersonAddDto personAddDto) {
		// findBy fonksiyonlarını service haline getir
		// convertToEntity
		// convertToDto servislerini oluştur
		if (this.personDao.findByPerson(personAddDto) != null) {
			return new ErrorDataResult<PersonDto>(this.personDao.findByPerson(personAddDto),
					"Bu nationalityId zaten kayıtlı.");
		} else {
			Person person = new Person();
			person.setNationalityId(personAddDto.getNationalityId());
			person.setFirstName(personAddDto.getFirstName());
			person.setLastName(personAddDto.getLastName());
			person.setDateOfBirth(new Date(personAddDto.getDateOfBirth().getYear(),
					personAddDto.getDateOfBirth().getMonth(), personAddDto.getDateOfBirth().getDate()));
			person.setDistrict(this.districtService.findById(personAddDto.getDistrictId()).getData());
			personDao.save(person);
			return new SuccessDataResult<PersonDto>(this.personDao.findByPerson(personAddDto), "başarılı");
		}
	}

	@Override
	public DataResult<List<PersonDto>> getAll() {
		return new SuccessDataResult<List<PersonDto>>(this.personDao.findAllPerson(), "Person Listelendi");
	}

	@Override
	public DataResult<PersonDto> findByFirstNameAndLastNameAndDateOfBirthAndProvince_ProvinceName(String firstName,
			String lastName, Date dateOfBirth, String provinceName) {
//		if(this.personDao.existsByFirstNameAndLastNameAndDateOfBirthAndProvince_ProvinceName(firstName, lastName, dateOfBirth, provinceName)) {
//			return new SuccessDataResult<Person>(this.personDao.findByFirstNameAndLastNameAndDateOfBirthAndProvince_ProvinceName(firstName, lastName, dateOfBirth, provinceName), "Person Bulundu");
//		}
//		else {
//			return new ErrorDataResult<Person>("Person Bulunamadı");
//		}
		return new SuccessDataResult<PersonDto>("person");
	}

	@Override
	public DataResult<PersonDto> findById(int id) {
		if (this.personDao.existsById(id)) {
			return new SuccessDataResult<PersonDto>(this.personDao.findById(id), "Person getirildi");
		} else {
			return new ErrorDataResult<PersonDto>("idler denk değil, gelen id: " + id + "test");
		}
	}

	@Override
	public DataResult<PersonDto> deleteById(int id) {
		return new SuccessDataResult<PersonDto>(this.personDao.deleteById(id), "Person silindi");
	}

	@SuppressWarnings("deprecation")
	@Override
	public DataResult<PersonDto> update(PersonUpdateDto personUpdateDto) {
		// findBy fonksiyonlarını service haline getir
		PersonDto personDto = personDao.findById(personUpdateDto.getPersonId());
		if (personDto == null) {
			return new ErrorDataResult<PersonDto>("Kullanıcı bulunamadı");
		}
		else if(!personDto.getNationalityId().equals(personUpdateDto.getNationalityId())) {
			// person nationalityId'leri farklı ise, daha önceden kullanılıyor mu diye kontrol et
			if(personDao.existsByNationalityId(personUpdateDto.getNationalityId())) {
				// nationalityId zaten kullanılıyor
				return new ErrorDataResult<PersonDto>("NationalityId Kullanımda");
			}
		}
		// ekleme yap
		Person person = new Person();
		person.setPersonId(personUpdateDto.getPersonId());
		person.setNationalityId(personUpdateDto.getNationalityId());
		person.setFirstName(personUpdateDto.getFirstName());
		person.setLastName(personUpdateDto.getLastName());
		person.setDateOfBirth(new Date(personUpdateDto.getDateOfBirth().getYear(),
				personUpdateDto.getDateOfBirth().getMonth(), personUpdateDto.getDateOfBirth().getDate()));
		person.setDistrict(this.districtService.findById(personUpdateDto.getDistrictId()).getData());
		personDao.save(person);
		return new SuccessDataResult<PersonDto>(personDao.findByPerson(personUpdateDto), "başarılı");
	}

	@Override
	public DataResult<List<PersonDto>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return new SuccessDataResult<List<PersonDto>>(this.personDao.findAllPerson(pageable).getContent(),
				"Person Listelendi");
	}

}
