package htp.controller.converters;

import htp.controller.request.UserFront;
import htp.domain.model.Roles;
import htp.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class UserFrontConverter extends GenericConverter <UserFront, User> {
    public static final String ROLE_USER = "ROLE_USER";

    public UserFrontConverter(BCryptPasswordEncoder cryptPassword) {
        this.cryptPassword = cryptPassword;
    }

    private BCryptPasswordEncoder cryptPassword;

    @Override
    public User convert(UserFront userFront) {
        User user = new User();
        user.setLogin(userFront.getLogin());
        user.setPassword(cryptPassword.encode(userFront.getPassword()));
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(user.getCreated());
        Set<Roles> roles = new HashSet<>();
        roles.add(new Roles(ROLE_USER, user));
        user.setRoles(roles);
        user.setIsdeleted(false);
        return user;
    }
}
