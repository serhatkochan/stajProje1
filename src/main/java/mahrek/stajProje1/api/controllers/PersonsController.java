package mahrek.stajProje1.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mahrek.stajProje1.business.abstracts.PersonService;
import mahrek.stajProje1.core.entities.Person;
import mahrek.stajProje1.core.entities.dtos.PersonAddDto;
import mahrek.stajProje1.core.entities.dtos.PersonDto;
import mahrek.stajProje1.core.entities.dtos.PersonUpdateDto;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.ErrorDataResult;

@RestController
@RequestMapping("/api/persons")
@CrossOrigin
public class PersonsController {
	
	private PersonService personService;
	
	@Autowired
	public PersonsController(PersonService personService) {
		super();
		this.personService = personService;
	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> add(@Valid @RequestBody PersonAddDto personAddDto){
		return ResponseEntity.ok(this.personService.add(personAddDto)); // hata yoksa this.userService.add çalışır
	}
	
	@GetMapping(value = "/findByPerson")
	public ResponseEntity<?> findByFirstNameAndLastNameAndDateOfBirthAndProvince_ProvinceName(@Valid @RequestBody Person person){ // spring-validation
		return ResponseEntity.ok(
				"ok"
				);
	}
	
	@GetMapping(value = "/deleteById")
	public ResponseEntity<?> deleteById(@Valid @RequestParam int personId){
		return ResponseEntity.ok(this.personService.deleteById(personId));
	}
	
	@GetMapping(value = "/getAll")
	public DataResult<List<PersonDto>> getAll(){
		return personService.getAll();
	}
	
	@PostMapping(value = "/update")
	public DataResult<PersonDto> update(@Valid @RequestBody PersonUpdateDto personUpdateDto) {
		return personService.update(personUpdateDto);
	}
	
	@GetMapping(value = "/findById")
	public DataResult<PersonDto> findById(@Valid @RequestParam int personId){
		return personService.findById(personId);
	}
	
	@GetMapping(value = "/getPage")
	public DataResult<List<PersonDto>> getAll(@RequestParam int pageNo, @RequestParam int pageSize){
		return personService.getAll(pageNo-1, pageSize);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
		Map<String, String> validationErrors = new HashMap<String, String>();
		for(FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		ErrorDataResult<Object> errors = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
		return errors;
	}

}
