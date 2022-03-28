package mahrek.stajProje1.core.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mahrek.stajProje1.core.entities.Province;
import mahrek.stajProje1.core.entities.dtos.ProvinceWithDistrictDto;


public interface ProvinceDao  extends JpaRepository<Province, Integer> {

	Province findById(int id);
	
	boolean existsById(int id);
	
//	Province findByProvinceName(String provinceName);
//	
//	boolean existsByProvinceName(String provinceName);
	
	Province deleteById(int provinceId);
	
//	@Query("Select new mahrek.stajProje1.core.entities.dtos.ProvinceWithDistrictDto(p.provinceName, d.districtName, p.provinceId) From District d Inner Join d.province p")
//	List<ProvinceWithDistrictDto> getProvinceWithDistrictDetails();
}
