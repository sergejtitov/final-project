package htp.dao;

import htp.domain.model.Application;

import java.util.List;

public interface ApplicationRepository extends GenericDao <Application, Long> {
    List<Application> findAll(int limit, int offset);
    List<Application> findApplicationByUserId (Long userId);
    List<Application> findApplicationByUserId (Long userId, int limit, int offset);
}

