package htp.controller.converters;

import htp.domain.model.Roles;
import htp.domain.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoleConverter extends GenericConverter <List<String>, Set<Roles>> {

    @Override
    public Set<Roles> convert(List<String> rolesList) {
        Set<Roles> roles = new HashSet<>();
        for (String role : rolesList){
            Roles newRole = new Roles();
            newRole.setName(role);
            roles.add(newRole);
        }
        return roles;
    }
}
