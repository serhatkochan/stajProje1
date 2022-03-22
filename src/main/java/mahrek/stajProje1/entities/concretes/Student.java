package mahrek.stajProje1.entities.concretes;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mahrek.stajProje1.core.entities.Person;
import mahrek.stajProje1.core.entities.Province;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private int studentId;
	
	@Column(name = "student_no")
	private String studentNo;
	
	@OneToOne(cascade = CascadeType.PERSIST) // cascade = CascadeType.ALL yaptığımızda student tarafından person da bir değişiklik yapıldığında personun kendisi de değişiyor
	@JoinColumn(name = "person_id")
	private Person person;
	
}
