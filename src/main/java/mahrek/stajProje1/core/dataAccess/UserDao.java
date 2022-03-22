package mahrek.stajProje1.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import mahrek.stajProje1.core.entities.User;

public interface UserDao extends JpaRepository<User, Integer>{
	
	User findById(int id);
	
	boolean existsById(int id);
	
	User findByEmailAndPassword(String email, String password);
	
	boolean existsByEmailAndPassword(String email, String password);
	
	User deleteById(int userId);
	
}
