package mahrek.stajProje1.business.abstracts;

import java.util.List;

import mahrek.stajProje1.core.entities.Province;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.Result;



public interface ProvinceService {

	DataResult<Province> add(Province province);
	
	DataResult<List<Province>> getAll();
	
	DataResult<Province> findByProvinceName(String provinceName);
	
	DataResult<Province> findById(int id);
	
	DataResult<Province> deleteById(int id);
	
	DataResult<Province> update(Province province);
	
	DataResult<List<Province>> getAll(int pageNo, int pageSize);
	
	
}
