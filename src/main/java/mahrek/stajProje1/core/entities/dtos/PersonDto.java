package mahrek.stajProje1.core.entities.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
	private int personId;
	private String nationalityId;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private int districtId;
	private String provinceName;
	private String districtName;
	
}
