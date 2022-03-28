package mahrek.stajProje1.core.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceWithDistrictDto {
	private int provinceId;
	private String provinceName;
	private String districtName;
}
