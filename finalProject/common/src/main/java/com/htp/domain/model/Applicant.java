package com.htp.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.htp.domain.dictionaries.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import static javax.persistence.EnumType.STRING;

@Slf4j
@Data
@AllArgsConstructor
@ToString(exclude = {"application","phones","addresses"})
@Entity
@Table(name = "m_applicant")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_id")
    private Long applicantId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column
    private String patronymic;

    @Column(name = "type_of_applicant")
    private Integer typeOfApplicant;

    @Column(name = "date_of_birthday")
    private Timestamp birthdayDate;

    @Column
    private Double income;

    @Enumerated(STRING)
    @Column
    private Gender sex = Gender.NOT_SELECTED;

    @Column
    private Integer experience;

    @Column(name = "marital_status")
    private Integer maritalStatus;

    @Column
    private Integer education;

    @Column(name = "children_quantity")
    private Integer childrenQuantity;

    @Column(name = "personal_number")
    private String personalNumber;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "applicant")
    private Set<Phone> phones;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "applicant")
    private Set<Address> addresses;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id")
    private Application application;



    public Applicant() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicantId, firstName, secondName, patronymic, typeOfApplicant,birthdayDate, income, sex, experience, maritalStatus, education, childrenQuantity, personalNumber);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return Objects.equals(applicantId, applicant.applicantId) &&
                Objects.equals(firstName, applicant.firstName) &&
                Objects.equals(secondName, applicant.secondName) &&
                Objects.equals(patronymic, applicant.patronymic) &&
                Objects.equals(typeOfApplicant, applicant.typeOfApplicant) &&
                Objects.equals(birthdayDate, applicant.birthdayDate) &&
                Objects.equals(income, applicant.income) &&
                Objects.equals(sex, applicant.sex) &&
                Objects.equals(experience, applicant.experience) &&
                Objects.equals(maritalStatus, applicant.maritalStatus) &&
                Objects.equals(education, applicant.education) &&
                Objects.equals(childrenQuantity, applicant.childrenQuantity) &&
                Objects.equals(personalNumber, applicant.personalNumber) ;
    }
}
