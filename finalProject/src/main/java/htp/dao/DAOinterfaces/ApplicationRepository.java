package htp.dao.DAOinterfaces;

import htp.entities.Application;

import java.util.List;

public interface ApplicationRepository extends GenericDao <Application, Long> {
    List<Application> findAll(int limit, int offset);
    List<Application> findSome (Long userId);
    List<Application> findSome (Long userId, int limit, int offset);
}

