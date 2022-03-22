package mahrek.stajProje1.business.concretes;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mahrek.stajProje1.business.abstracts.PersonService;
import mahrek.stajProje1.core.dataAccess.PersonDao;
import mahrek.stajProje1.core.dataAccess.ProvinceDao;
import mahrek.stajProje1.core.entities.Person;
import mahrek.stajProje1.core.entities.Province;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.ErrorDataResult;
import mahrek.stajProje1.core.utilities.results.Result;
import mahrek.stajProje1.core.utilities.results.SuccessDataResult;
import mahrek.stajProje1.core.utilities.results.SuccessResult;

@Service
public class PersonManager implements PersonService {

	private PersonDao personDao;
	private ProvinceDao provinceDao;
	
	@Autowired
	public PersonManager(PersonDao personDao, ProvinceDao provinceDao) {
		this.personDao = personDao;
		this.provinceDao = provinceDao;
	}

	@Override
	public DataResult<Person> add(Person person) {
		if(this.personDao.existsByFirstNameAndLastNameAndDateOfBirthAndProvince_ProvinceName(
				person.getFirstName(), person.getLastName(), person.getDateOfBirth(), person.getProvince().getProvinceName())) {
			return new ErrorDataResult<Person>(person, "Bu Person zaten var.");
		}
		else {
			if(provinceDao.existsByProvinceName(person.getProvince().getProvinceName())) {
				person.setProvince(provinceDao.findByProvinceName(person.getProvince().getProvinceName()));
				this.personDao.save(person);		
				return new SuccessDataResult<Person>(person, "Person eklendi");
			}
			else {
				return new ErrorDataResult<Person>(person, "Bu Person için Province Bilgisi Gelmedi");			
			}
		}
//		return new SuccessDataResult<Person>("hata yok, " + person.getFirstName()+ person.getLastName()+ person.getDateOfBirth() + person.getProvince().getProvinceName());
	}

	@Override
	public DataResult<List<Person>> getAll() {
		return new SuccessDataResult<List<Person>>(this.personDao.findAll(), "Person Listelendi");
	}

	@Override
	public DataResult<Person> findByFirstNameAndLastNameAndDateOfBirthAndProvince_ProvinceName(String firstName, String lastName, Date dateOfBirth, String provinceName) {
		if(this.personDao.existsByFirstNameAndLastNameAndDateOfBirthAndProvince_ProvinceName(firstName, lastName, dateOfBirth, provinceName)) {
			return new SuccessDataResult<Person>(this.personDao.findByFirstNameAndLastNameAndDateOfBirthAndProvince_ProvinceName(firstName, lastName, dateOfBirth, provinceName), "Person Bulundu");
		}
		else {
			return new ErrorDataResult<Person>("Person Bulunamadı");
		}
	}

	@Override
	public DataResult<Person> findById(int id) {
		if(this.personDao.existsById(id)) {
			return new SuccessDataResult<Person>(this.personDao.findById(id), "Person getirildi");
		}
		else {
			return new ErrorDataResult<Person>("idler denk değil, gelen id: " + id + "test");
		}
	}

	@Override
	public DataResult<Person> deleteById(int id) {
		return new SuccessDataResult<Person>(this.personDao.deleteById(id), "Person silindi");
	}

	@Override
	public DataResult<Person> update(Person person) {
		if(findById(person.getPersonId()).isSuccess()) {
			if(provinceDao.existsByProvinceName(person.getProvince().getProvinceName())) {
				person.setProvince(provinceDao.findByProvinceName(person.getProvince().getProvinceName()));
				this.personDao.save(person);		
				return new SuccessDataResult<Person>(person, "Person Güncellendi");
			}
			else {
					return new ErrorDataResult<Person>(person, "Bu Person için Province Bilgisi Gelmedi");			
			}
		}
		else {
			return new ErrorDataResult<Person>("Person bulunamadı." + person.getPersonId());
		}
		
		
//		return new SuccessDataResult<Person>("hata yok, " 
//					+ person.getFirstName()
//					+ person.getLastName()
//					+ person.getDateOfBirth()
//					+ person.getProvince().getProvinceName()
//					+ person.getProvince().getProvinceId());
//	
//	}
	}

	@Override
	public DataResult<List<Person>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return new SuccessDataResult<List<Person>>(this.personDao.findAll(pageable).getContent(), "Person Listelendi");
	}
	
}
