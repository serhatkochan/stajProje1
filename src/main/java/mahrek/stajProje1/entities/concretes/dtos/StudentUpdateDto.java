package mahrek.stajProje1.entities.concretes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateDto {
	private int studentId;
	private String studentNo;
	private int personId;

}
