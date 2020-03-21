package htp.services;


import htp.dao.RolesRepository;
import htp.dao.UserRepository;
import htp.dao.hibernate_Impl.UserHibernateImpl;
import htp.entities.db_entities.Roles;
import htp.entities.db_entities.User;
import htp.exceptions.NoSuchEntityException;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService  {

    private UserRepository userDao;
    private RolesRepository rolesDao;
    private BCryptPasswordEncoder cryptPassword;

    public UserDetailsServiceImpl(UserHibernateImpl userDao, RolesRepository rolesDao, BCryptPasswordEncoder cryptPassword) {
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
        Optional<User> user = Optional.ofNullable(userDao.findById(userId));
        return user.orElse(new User());
    }

    public List<User> findAll(int limit, int offset){
        return userDao.findAll(limit,offset);
    }

    public User saveUser(User entity){
        User user = userDao.findByLogin(entity.getLogin());
        if (user.getUserId() != null){
            return new User();
        }
        entity.setPassword(cryptPassword.encode(entity.getPassword()));
            return userDao.save(entity);
    }

    public User updateUser(User entity){
        entity.setPassword(cryptPassword.encode(entity.getPassword()));
        return userDao.update(entity);
    }

    public void deleteUser (Long userId){
        if( userDao.findById(userId).getUserId() == null){
            throw new NoSuchEntityException("No such user!");
        }
        userDao.delete(userId);
    }

    public void fakeDelete(Long userId){
        userDao.fakeDelete(userId);
    }
}

