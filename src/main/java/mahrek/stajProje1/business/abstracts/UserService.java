package mahrek.stajProje1.business.abstracts;

import java.util.List;

import mahrek.stajProje1.core.entities.User;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.Result;

public interface UserService {
	
	Result add(User user);
	
	DataResult<User> findByEmailAndPassword(String email, String password);
	
	DataResult<User> deleteById(int userId);
	
	DataResult<List<User>> getAll();
	
	DataResult<User> findById(int id);
	
	Result update(User user);
	
	DataResult<List<User>> getAll(int pageNo, int pageSize);

}
