package mahrek.stajProje1.business.abstracts;

import mahrek.stajProje1.core.entities.District;
import mahrek.stajProje1.core.utilities.results.DataResult;

public interface DistrictService {
	
	DataResult<District> findById(int districtId);

}
