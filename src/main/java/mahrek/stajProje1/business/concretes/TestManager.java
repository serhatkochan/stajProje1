package mahrek.stajProje1.business.concretes;

import mahrek.stajProje1.core.utilities.converter.EntityDTOConverter;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.ErrorDataResult;
import mahrek.stajProje1.core.utilities.results.SuccessDataResult;
import mahrek.stajProje1.dataAccess.abstracts.StudentDao;
import mahrek.stajProje1.entities.concretes.Student;
import mahrek.stajProje1.entities.concretes.dtos.StudentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestManager {

    @Autowired
    private StudentDao studentDao;

    private EntityDTOConverter<Student, StudentDto> studentToStudentDtoConverter = new EntityDTOConverter(StudentDto.class);

    public DataResult<List<StudentDto>> getAllStudent() {
        return new SuccessDataResult<List<StudentDto>>(studentDao.findAllStudent());
    }

    public DataResult<List<Student>> getAllStudentEntity(){
        return new SuccessDataResult<List<Student>>(studentDao.findAll());
    }

    public DataResult<List<StudentDto>> getAllStudentConverter() {
        try {
            List<StudentDto> studentDtoList = studentDao.findAll()
                    .stream()
                    .map(studentToStudentDtoConverter::convert)
                    .collect(Collectors.toCollection(ArrayList::new));
            return new SuccessDataResult<List<StudentDto>>(studentDtoList, "asdas");
        } catch (Exception ex) {
            return new ErrorDataResult<List<StudentDto>>("hata: " + ex);
        }
    }
}
