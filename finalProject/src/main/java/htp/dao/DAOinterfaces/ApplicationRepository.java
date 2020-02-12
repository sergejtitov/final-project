package htp.dao.DAOinterfaces;

import htp.entities.Application;

import java.util.List;

public interface ApplicationRepository {
    int save (Application application);
    Application findOne (String applicationId);
    List<Application> findAll();
    List<Application> findSome (Long userId);
    int update (Application application);
    void delete (String applicationId);
}

