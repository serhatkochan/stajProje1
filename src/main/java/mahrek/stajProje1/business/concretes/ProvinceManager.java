package mahrek.stajProje1.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import mahrek.stajProje1.business.abstracts.ProvinceService;
import mahrek.stajProje1.core.dataAccess.ProvinceDao;
import mahrek.stajProje1.core.entities.Province;
import mahrek.stajProje1.core.entities.User;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.ErrorDataResult;
import mahrek.stajProje1.core.utilities.results.ErrorResult;
import mahrek.stajProje1.core.utilities.results.Result;
import mahrek.stajProje1.core.utilities.results.SuccessDataResult;
import mahrek.stajProje1.core.utilities.results.SuccessResult;

@Service
public class ProvinceManager implements ProvinceService {

	private ProvinceDao provinceDao;
	
	@Autowired
	public ProvinceManager(ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}

	@Override
	public DataResult<Province> add(Province province) {
//		if(this.provinceDao.existsByProvinceName(province.getProvinceName())) {
//			return new ErrorDataResult<Province>("Bu Province zaten var.");
//		}
//		else {
//			this.provinceDao.save(province);		
//			return new SuccessDataResult<Province>("Province eklendi");
//		}
		return new SuccessDataResult<Province>("Province");
	}

	@Override
	public DataResult<List<Province>> getAll() {
		return new SuccessDataResult<List<Province>>(this.provinceDao.findAll(), "Province Listelendi");
	}

	@Override
	public DataResult<Province> findByProvinceName(String provinceName) {
//		if(this.provinceDao.existsByProvinceName(provinceName)) {
//			return new SuccessDataResult<Province>(this.provinceDao.findByProvinceName(provinceName), "Province Bulundu");
//		}
//		else {
//			return new ErrorDataResult<Province>("Kullanıcı Bulunamadı");
//		}
		return new SuccessDataResult<Province>("Province");
	}

	@Override
	public DataResult<Province> findById(int id) {
		if(this.provinceDao.existsById(id)) {
			return new SuccessDataResult<Province>(this.provinceDao.findById(id), "Province getirildi");
		}
		else {
			return new ErrorDataResult<Province>("province idler denk değil");
		}
	}

	@Override
	public DataResult<Province> deleteById(int id) {
		return new SuccessDataResult<Province>(this.provinceDao.deleteById(id), "Province silindi");
	}

	@Override
	public DataResult<Province> update(Province province) {
//		if(findById(province.getProvinceId()).isSuccess()) {
//			if(this.provinceDao.existsByProvinceName(province.getProvinceName())) {
//				return new ErrorDataResult<Province>("Bu Province zaten var.");
//			}
//			else {
//				provinceDao.save(province);
//				return new SuccessDataResult<Province>("Province Güncellendi");
//			}
//		}
//		else {
//			return new ErrorDataResult<Province>("Province bulunamadı.");
//		}
		return new SuccessDataResult<Province>("Province");
		
	}

	@Override
	public DataResult<List<Province>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return new SuccessDataResult<List<Province>>(this.provinceDao.findAll(pageable).getContent(), "Province Listelendi");
	}

}
