package mahrek.stajProje1.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mahrek.stajProje1.business.abstracts.DistrictService;
import mahrek.stajProje1.core.dataAccess.DistrictDao;
import mahrek.stajProje1.core.entities.District;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.SuccessDataResult;

@Service
public class DistrictManager implements DistrictService {
	
	private DistrictDao districtDao;
	
	@Autowired
	public DistrictManager(DistrictDao districtDao) {
		this.districtDao = districtDao;
	}

	@Override
	public DataResult<District> findById(int districtId) {
		return new SuccessDataResult<District>(districtDao.findById(districtId));
	}

}
