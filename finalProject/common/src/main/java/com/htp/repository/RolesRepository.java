package com.htp.repository;

import com.htp.domain.model.Roles;

import java.util.List;



public interface RolesRepository extends GenericDao<Roles, Long> {
    List<Roles> findRolesByUserId(Long id);

}
