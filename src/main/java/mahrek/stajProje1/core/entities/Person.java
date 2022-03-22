package mahrek.stajProje1.core.entities;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mahrek.stajProje1.entities.concretes.Student;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "persons")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "student"})
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "person_id")
	private int personId;
	
	// nationalityId
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
//	@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne()
	@JoinColumn(name = "province_id")
	private Province province;
	
	//mappedBy = "student"
	@OneToOne(mappedBy = "person", cascade = CascadeType.REMOVE) // , orphanRemoval=true // cascade kaldırdık, student silindiğinde person kalıyor artık.
	private Student student;
	
	

}