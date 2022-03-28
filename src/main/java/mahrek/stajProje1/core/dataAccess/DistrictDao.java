package mahrek.stajProje1.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import mahrek.stajProje1.core.entities.District;

public interface DistrictDao extends JpaRepository<District, Integer> {
	
	District findById(int districtId);

}
