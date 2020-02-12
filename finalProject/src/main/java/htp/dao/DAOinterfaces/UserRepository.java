package htp.dao.DAOinterfaces;

import htp.entities.User;

import java.util.List;

public interface UserRepository extends GenericDao<User, Long> {
    List<User> findAll ();
    void fakeDelete (Long id);
    User findByLogin(String login);
}


