package htp.controller.converters;

import htp.controller.request.UserFrontAdmin;
import htp.domain.model.Roles;
import htp.domain.model.User;
import htp.utils.Parsers;
import org.springframework.core.convert.ConversionService;

import java.sql.Timestamp;
import java.util.Set;

public class UserFrontAdminConverter extends GenericConverter <UserFrontAdmin, User> {


    @Override
    public User convert(UserFrontAdmin userFrontAdmin) {
        User user = new User();
        user.setLogin(userFrontAdmin.getLogin());
        user.setPassword(userFrontAdmin.getPassword());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(user.getCreated());
        user.setRoles(Parsers.getSetOfRoles(userFrontAdmin.getRole(), user));
        user.setIsdeleted(false);
        return user;
    }
}
