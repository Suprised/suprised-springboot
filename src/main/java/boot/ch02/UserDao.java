package boot.ch02;

import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long>{

}
