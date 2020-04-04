package htp.dao.spring_data;

import htp.domain.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationDataRepository extends CrudRepository<Application, Long>, JpaRepository<Application, Long> {
}
