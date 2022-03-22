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

import mahrek.stajProje1.business.abstracts.ProvinceService;
import mahrek.stajProje1.core.entities.Province;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.ErrorDataResult;


@RestController
@RequestMapping("/api/provinces")
@CrossOrigin
public class ProvincesController {
	
	private ProvinceService provinceService;
	
	@Autowired
	public ProvincesController(ProvinceService provinceService) {
		super();
		this.provinceService = provinceService;
	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> add(@Valid @RequestBody Province province){
		return ResponseEntity.ok(this.provinceService.add(province)); // hata yoksa this.userService.add çalışır
	}
	
	@GetMapping(value = "/findByProvinceName")
	public ResponseEntity<?> findByProvinceName(@Valid @RequestParam String provinceName){ // spring-validation
		return ResponseEntity.ok(this.provinceService.findByProvinceName(provinceName));
	}
	
	@GetMapping(value = "/deleteById")
	public ResponseEntity<?> deleteById(@Valid @RequestParam int provinceId){
		return ResponseEntity.ok(this.provinceService.deleteById(provinceId));
	}
	
	@GetMapping(value = "/getAll")
	public DataResult<List<Province>> getAll(){
		return provinceService.getAll();
	}
	
	@PostMapping(value = "/update")
	public DataResult<Province> update(@Valid @RequestBody Province province) {
		return provinceService.update(province);
	}
	
	@GetMapping(value = "/findById")
	public DataResult<Province> findById(@Valid @RequestParam int id){
		return provinceService.findById(id);
	}
	
	@GetMapping(value = "/getPage")
	public DataResult<List<Province>> getAll(@RequestParam int pageNo, @RequestParam int pageSize){
		return provinceService.getAll(pageNo-1, pageSize);
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
