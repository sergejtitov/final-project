package htp.dao.spring_data;

import htp.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends CrudRepository<User, Long>, JpaRepository<User,Long> {
     User findByLogin (String login);
     User findByUserId (Long userId);
}
