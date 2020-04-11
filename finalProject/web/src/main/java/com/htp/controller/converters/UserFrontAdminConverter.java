package com.htp.controller.converters;

import com.htp.controller.request.UserFrontAdmin;
import com.htp.Utils.Parsers;
import com.htp.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Slf4j
@Component
public class UserFrontAdminConverter extends GenericConverter <UserFrontAdmin, User> {
    private BCryptPasswordEncoder cryptPassword;

    public UserFrontAdminConverter(BCryptPasswordEncoder cryptPassword) {
        this.cryptPassword = cryptPassword;
    }

    @Override
    public User convert(UserFrontAdmin userFrontAdmin) {
        User user = new User();
        user.setLogin(userFrontAdmin.getLogin());
        user.setPassword(cryptPassword.encode(userFrontAdmin.getPassword()));
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(user.getCreated());
        user.setRoles(Parsers.getSetOfRoles(userFrontAdmin.getRole(), user));
        user.setIsdeleted(false);
        return user;
    }
}
