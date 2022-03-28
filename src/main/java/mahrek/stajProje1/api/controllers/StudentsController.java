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

import mahrek.stajProje1.business.abstracts.StudentService;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.ErrorDataResult;
import mahrek.stajProje1.entities.concretes.Student;
import mahrek.stajProje1.entities.concretes.dtos.StudentAddDto;
import mahrek.stajProje1.entities.concretes.dtos.StudentDto;
import mahrek.stajProje1.entities.concretes.dtos.StudentUpdateDto; 

@RestController
@RequestMapping("api/students")
@CrossOrigin
public class StudentsController {
	
	StudentService studentService;
	
	@Autowired
	public StudentsController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping("/getAll")
	public DataResult<List<StudentDto>> getAll(){
		return this.studentService.getAll();
	}
	
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> add(@Valid @RequestBody StudentAddDto studentAddDto){
		return ResponseEntity.ok(this.studentService.add(studentAddDto)); // hata yoksa this.userService.add çalışır
	}
	
	
	@GetMapping(value = "/getPage")
	public DataResult<List<StudentDto>> getAll(@RequestParam int pageNo, @RequestParam int pageSize){
		return studentService.getAll(pageNo-1, pageSize);
	}
	
	@GetMapping(value = "/deleteById")
	public ResponseEntity<?> deleteById(@Valid @RequestParam int studentId){
		return ResponseEntity.ok(this.studentService.deleteById(studentId));
	}
	
	@GetMapping(value = "/findById")
	public ResponseEntity<?> findById(@RequestParam int studentId){
		return ResponseEntity.ok(this.studentService.findById(studentId));
	}
	
	@PostMapping(value = "/update")
	public ResponseEntity<?> update(@Valid @RequestBody StudentUpdateDto studentUpdateDto){
		return ResponseEntity.ok(this.studentService.update(studentUpdateDto));
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
	// error yapısını oluştur.
	// userCheck, studentCheck sistemini kur.
	
	
}
