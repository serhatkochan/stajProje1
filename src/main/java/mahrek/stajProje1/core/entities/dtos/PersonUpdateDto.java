package mahrek.stajProje1.core.entities.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonUpdateDto {
	private int userId;
	private String email;
	private String password;
	private String nationalityId;
	private int personId;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private int districtId;
}
