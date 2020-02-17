package htp.dao.DAOinterfaces;

import htp.entities.Applicant;

import java.util.List;

public interface ApplicantRepository extends GenericDao<Applicant, Long> {
    List<Applicant> findAll(int limit, int offset);
    List<Applicant> findByApplication(Long application_id);
}
