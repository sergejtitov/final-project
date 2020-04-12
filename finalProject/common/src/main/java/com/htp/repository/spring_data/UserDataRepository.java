package com.htp.repository.spring_data;

import com.htp.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends CrudRepository<User, Long>, JpaRepository<User,Long> {
     Optional<User> findByLogin (String login);
     User findByUserId (Long userId);
}
