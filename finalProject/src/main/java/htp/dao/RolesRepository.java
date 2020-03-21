package htp.dao;

import htp.entities.db_entities.Roles;

import java.util.List;



public interface RolesRepository extends GenericDao<Roles, Long> {
    public List<Roles> findRolesByUserId(Long id);

}
