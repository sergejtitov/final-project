package htp.services;

import htp.dao.spring_data.UserDataRepository;
import htp.domain.model.Roles;
import htp.domain.model.User;
import htp.exceptions.EntityAlreadyExists;
import htp.exceptions.NoSuchEntityException;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

    private UserDataRepository userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<User> searchUser = userDao.findByLogin(username);
            if (searchUser.isPresent()) {
                User user = searchUser.get();
                return new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles().stream().map(Roles::getName).collect(Collectors.joining(",")))
                );
            } else {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            }
        } catch (Exception e){
            throw new UsernameNotFoundException("User with this login not found");
        }
    }

    public User findUserById(Long userId){
        Optional<User> user = Optional.ofNullable(userDao.findByUserId(userId));
        return user.orElseThrow(()-> new NoSuchEntityException("No such User"));
    }

    public User findByLogin(String login){
        Optional<User> user = userDao.findByLogin(login);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NoSuchEntityException("No such User");
        }
    }

    public Page<User> findAll(int limit, int offset){
        return userDao.findAll(PageRequest.of(offset, limit));
    }

   @Transactional(rollbackFor = Exception.class)
    public User saveUser(User entity){
        Optional<User> user = userDao.findByLogin(entity.getLogin());
        if (user.isPresent()){
            throw new EntityAlreadyExists("Such user already exists!");
        }
        entity.setPassword(entity.getPassword());
            return userDao.saveAndFlush(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public User updateUser(User entity){
        entity.setPassword(entity.getPassword());
        return userDao.save(entity);
    }

    public void deleteUser (Long userId){
        User userToDelete = userDao.findByUserId(userId);
        if( userToDelete.getUserId() == null){
            throw new NoSuchEntityException("No such user!");
        }
        userDao.delete(userToDelete);
    }

    @Transactional(rollbackFor = Exception.class)
    public void fakeDelete(Long userId){
        User userToFakeDelete = userDao.findByUserId(userId);
        userToFakeDelete.setIsdeleted(true);
        userDao.save(userToFakeDelete);
    }
}

