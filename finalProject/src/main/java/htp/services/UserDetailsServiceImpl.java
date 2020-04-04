package htp.services;


import htp.dao.RolesRepository;
import htp.dao.spring_data.UserDataRepository;
import htp.domain.model.Roles;
import htp.domain.model.User;
import htp.exceptions.NoSuchEntityException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

    private UserDataRepository userDao;
    private RolesRepository rolesDao;
    private BCryptPasswordEncoder cryptPassword;

    public UserDetailsServiceImpl(UserDataRepository userDao, RolesRepository rolesDao, BCryptPasswordEncoder cryptPassword) {
        this.userDao = userDao;
        this.rolesDao = rolesDao;
        this.cryptPassword = cryptPassword;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByLogin(username);
        List<Roles> roles = rolesDao.findRolesByUserId(user.getUserId());
        if (user.getUserId() == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(),user.getPassword(), user.isEnabled(), user.isAccountNonExpired(),user.isCredentialsNonExpired(),user.isAccountNonLocked(),AuthorityUtils.commaSeparatedStringToAuthorityList(roles.get(0).getName()));

    }

    public User findUserById(Long userId){
        Optional<User> user = Optional.ofNullable(userDao.findByUserId(userId));
        return user.orElseThrow(()-> new NoSuchEntityException("No such User"));
    }

    public Page<User> findAll(int limit, int offset){
        return userDao.findAll(PageRequest.of(offset, limit));
    }

   @Transactional
    public User saveUser(User entity){
        User user = userDao.findByLogin(entity.getLogin());
        if (user!= null){
            return new User();
        }
        entity.setPassword(cryptPassword.encode(entity.getPassword()));
            return userDao.saveAndFlush(entity);
    }

    public User updateUser(User entity){
        entity.setPassword(cryptPassword.encode(entity.getPassword()));
        return userDao.save(entity);
    }

    public void deleteUser (Long userId){
        User userToDelete = userDao.findByUserId(userId);
        if( userToDelete.getUserId() == null){
            throw new NoSuchEntityException("No such user!");
        }
        userDao.delete(userToDelete);
    }

    public void fakeDelete(Long userId){
        User userToFakeDelete = userDao.findByUserId(userId);
        userToFakeDelete.setIsdeleted(true);
        userDao.save(userToFakeDelete);
    }
}

