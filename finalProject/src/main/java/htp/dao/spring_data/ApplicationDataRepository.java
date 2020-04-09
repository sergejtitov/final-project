package htp.dao.spring_data;

import htp.domain.model.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationDataRepository extends CrudRepository<Application, Long>, JpaRepository<Application, Long>, JpaSpecificationExecutor<Application> {

    List<Application> findApplicationByUserId(Long userId);
}
