package mahrek.stajProje1.core.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "districts")
@JsonIgnoreProperties({"persons", "province"})
public class District { // ilçe
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "district_id")
	private int districtId;
	
	
	@Column(name = "district_name")
	private String districtName;
	
	@ManyToOne()
	@JoinColumn(name = "province_no", insertable = false, updatable = false)
	private Province province;
	
	@OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
	private List<Person> persons;

}
