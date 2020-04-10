package htp.services;

import htp.dao.RolesRepository;
import htp.dao.UserRepository;
import htp.dao.hibernate_Impl.UserHibernateImpl;
import htp.domain.model.Roles;
import htp.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private UserRepository userDao;
    private RolesRepository rolesDao;
    private BCryptPasswordEncoder passwordEncoder;

    public AuthProviderImpl(UserHibernateImpl userDao, RolesRepository rolesDao, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.rolesDao = rolesDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        User user = userDao.findByLogin(login);
        if (user.getUserId()==null){
            throw new UsernameNotFoundException("User not found");
        }
        String password = authentication.getCredentials().toString();
        password = passwordEncoder.encode(password);
        if (!password.equals(user.getPassword())){
            throw new BadCredentialsException("Bad Credentials");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Roles roles : rolesDao.findRolesByUserId(user.getUserId())){
            authorities.add(new SimpleGrantedAuthority(roles.getName()));
        }
        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
