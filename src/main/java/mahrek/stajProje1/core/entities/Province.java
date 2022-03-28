package mahrek.stajProje1.core.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mahrek.stajProje1.entities.concretes.Student;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "provinces")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Province {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "province_id")
	private int provinceId;
	
	@Column(name = "province_no")
	private String provinceNo;
	
	@Column(name = "province_name")
	private String provinceName;
	
//	@OneToMany(mappedBy = "province", cascade = CascadeType.ALL)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "province_no")
	private List<District> districts;
	
	

}
