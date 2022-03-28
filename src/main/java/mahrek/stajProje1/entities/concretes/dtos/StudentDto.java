package mahrek.stajProje1.entities.concretes.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
	private int studentId; // student
	private String studentNo;
	private int personId; // person
	private String nationalityId;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String provinceName;
	private int districtId;
	private String districtName;
	
	
}
