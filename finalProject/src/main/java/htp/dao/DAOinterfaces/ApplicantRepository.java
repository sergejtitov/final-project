package htp.dao.DAOinterfaces;

import htp.entities.Applicant;

import java.util.List;

public interface ApplicantRepository extends GenericDao<Applicant, Long> {
    List<Applicant> findAll();
}
