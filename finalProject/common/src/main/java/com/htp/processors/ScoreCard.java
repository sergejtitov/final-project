package com.htp.processors;

import com.htp.domain.wrappers.ApplicantWrapper;

public interface ScoreCard {
    Integer calculateScore(ApplicantWrapper applicantWrapper);
    Integer getDeclinedScore ();
}
