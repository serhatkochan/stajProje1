package mahrek.stajProje1.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import mahrek.stajProje1.core.entities.Province;
import mahrek.stajProje1.core.entities.User;


public interface ProvinceDao  extends JpaRepository<Province, Integer> {

	Province findById(int id);
	
	boolean existsById(int id);
	
//	Province findByProvinceName(String provinceName);
//	
//	boolean existsByProvinceName(String provinceName);
	
	Province deleteById(int provinceId);
}
