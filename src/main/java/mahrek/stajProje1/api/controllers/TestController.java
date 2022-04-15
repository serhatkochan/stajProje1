package mahrek.stajProje1.api.controllers;

import mahrek.stajProje1.business.concretes.TestManager;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.SuccessDataResult;
import mahrek.stajProje1.entities.concretes.dtos.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    private TestManager testManager;

    @GetMapping("/getAllStudent")
    public ResponseEntity<?> getAllStudent() {
        return ResponseEntity.ok(testManager.getAllStudent());
    }

    @GetMapping("/getAllStudentToConverter")
    public ResponseEntity<?> getAllStudentToConverter(){
        return ResponseEntity.ok(testManager.getAllStudentConverter());
    }

    @GetMapping("getAllStudentEntity")
    public ResponseEntity<?> getAllStudentEntity(){
        return ResponseEntity.ok(testManager.getAllStudentEntity());
    }
}
