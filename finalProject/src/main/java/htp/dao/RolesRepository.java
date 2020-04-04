package htp.dao;

import htp.domain.model.Roles;

import java.util.List;



public interface RolesRepository extends GenericDao<Roles, Long> {
    List<Roles> findRolesByUserId(Long id);

}
