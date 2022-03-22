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

import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.ErrorDataResult;
import mahrek.stajProje1.core.utilities.results.Result;
import mahrek.stajProje1.business.abstracts.UserService;
import mahrek.stajProje1.core.entities.User;

@RestController
@RequestMapping("api/users")
@CrossOrigin
public class UsersController {
	
	UserService userService;
	
	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<?> add(@Valid @RequestBody User user){
		return ResponseEntity.ok(this.userService.add(user)); // hata yoksa this.userService.add çalışır
	}
	
	@GetMapping(value = "/findByEmailAndPassword")
	public ResponseEntity<?> findByEmailAndPassword(@Valid @RequestParam String email, @Valid @RequestParam String password){ // spring-validation
		return ResponseEntity.ok(this.userService.findByEmailAndPassword(email, password));
	}
	
	@GetMapping(value = "/deleteById")
	public ResponseEntity<?> deleteById(@Valid @RequestParam int userId){
		return ResponseEntity.ok(this.userService.deleteById(userId));
	}
	
	@GetMapping(value = "/getAll")
	public DataResult<List<User>> getAll(){
		return userService.getAll();
	}
	
	@PostMapping(value = "/update")
	public Result update(@Valid @RequestBody User user) {
		return userService.update(user);
	}
	
	@GetMapping(value = "/findById")
	public DataResult<User> findById(@Valid @RequestParam int id){
		return userService.findById(id);
	}
	
	@GetMapping(value = "/getPage")
	public DataResult<List<User>> getAll(@RequestParam int pageNo, @RequestParam int pageSize){
		return userService.getAll(pageNo-1, pageSize);
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
