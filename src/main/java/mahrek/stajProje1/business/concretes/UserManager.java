package mahrek.stajProje1.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import mahrek.stajProje1.core.utilities.results.SuccessResult;
import mahrek.stajProje1.business.abstracts.UserService;
import mahrek.stajProje1.core.dataAccess.UserDao;
import mahrek.stajProje1.core.entities.User;
import mahrek.stajProje1.core.utilities.results.DataResult;
import mahrek.stajProje1.core.utilities.results.ErrorDataResult;
import mahrek.stajProje1.core.utilities.results.ErrorResult;
import mahrek.stajProje1.core.utilities.results.Result;
import mahrek.stajProje1.core.utilities.results.SuccessDataResult;

@Service
public class UserManager implements UserService{
	
	private UserDao userDao;
	
	@Autowired
	public UserManager(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Result add(User user) {
		this.userDao.save(user);
		return new SuccessResult("Kullanıcı eklendi.");
	}

	@Override
	public DataResult<User> findByEmailAndPassword(String email, String password) {
		if(this.userDao.existsByEmailAndPassword(email, password)) {
			return new SuccessDataResult<User>(this.userDao.findByEmailAndPassword(email, password), "Kullanıcı Bulundu");
		}
		else {
			return new ErrorDataResult<User>("Kullanıcı Bulunamadı");
		}
	}
	
	@Override
	public DataResult<User> deleteById(int userId) {
		return new SuccessDataResult<User>(this.userDao.deleteById(userId), "User silindi");
	}

	@Override
	public DataResult<List<User>> getAll() {
		return new SuccessDataResult<List<User>>(this.userDao.findAll(), "User listelendi");
	}

	@Override
	public DataResult<User> findById(int id) {
		if(this.userDao.existsById(id)) {
			return new SuccessDataResult<User>(this.userDao.findById(id), "User getirildi");
		}
		else {
			return new ErrorDataResult<User>("idler denk değil");
		}
	}
	@Override
	public Result update(User user) {
		if(findById(user.getUserId()).isSuccess()) {
			userDao.save(user);
			return new SuccessResult("Kullanıcı Güncellendi");
		}
		else {
			return new ErrorResult("Kullanıcı bulunamadı.");
		}
	}
	
	@Override
	public DataResult<List<User>> getAll(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		return new SuccessDataResult<List<User>>(this.userDao.findAll(pageable).getContent(), "User Listelendi");
	}


}
