package com.htp.repository;

import com.htp.domain.model.Applicant;

import java.util.List;

public interface ApplicantRepository extends GenericDao<Applicant, Long> {
    List<Applicant> findAll(int limit, int offset);
    List<Applicant> findApplicantByApplication(Long application_id);
}
