package htp.dao;

import htp.domain.model.User;

import java.util.List;

public interface UserRepository extends GenericDao<User, Long> {
    List<User> findAll (int limit, int offset);
    void fakeDelete (Long id);
    User findByLogin(String login);
}


