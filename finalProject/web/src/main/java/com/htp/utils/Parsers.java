package com.htp.utils;

import com.htp.controller.request.AddressFront;
import com.htp.controller.request.ApplicantFront;
import com.htp.controller.request.PhoneFront;
import com.htp.domain.dictionaries.Gender;
import com.htp.domain.model.Roles;
import com.htp.domain.model.User;
import com.htp.domain.model.Phone;
import com.htp.domain.model.Address;
import com.htp.domain.model.Applicant;
import com.htp.domain.model.Application;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Data
public class Parsers {



    public static Set<Roles> getSetOfRoles(List<String> list, User user){
        Set<Roles> roles = new HashSet<>();
        for (String role : list){
            Roles newRole = new Roles();
            newRole.setName(role);
            newRole.setUser(user);
            roles.add(newRole);
        }
        return roles;
    }

    public static Set<Phone> createSetPhones(List<PhoneFront> phoneFronts, Applicant applicant){
        Set<Phone> phones = new HashSet<>();
        for (PhoneFront phoneFront: phoneFronts){
            Phone phone = new Phone();
            phone.setPhoneNumber(phoneFront.getPhoneNumber());
            phone.setPhoneType(phoneFront.getPhoneType());
            phone.setApplicant(applicant);
            phones.add(phone);
        }
        return phones;
    }

    public static Set<Address> createSetAddresses(List<AddressFront> addressFronts, Applicant applicant){
        Set<Address> addresses = new HashSet<>();
        for (AddressFront addressFront: addressFronts){
            Address address = new Address();
            address.setAddressType(addressFront.getAddressType());
            address.setAddressDescription(addressFront.getAddress());
            address.setApplicant(applicant);
            addresses.add(address);
        }
        return addresses;
    }

    public static Set<Applicant> createSetApplicants(List<ApplicantFront> applicantsFront, Application application){
        Set<Applicant> applicants = new HashSet<>();
        for (ApplicantFront applicantFront : applicantsFront){
            Applicant applicant = new Applicant();
            applicant.setFirstName(applicantFront.getFirstName());
            applicant.setSecondName(applicantFront.getSecondName());
            applicant.setPatronymic(applicantFront.getPatronymic());
            applicant.setTypeOfApplicant(applicantFront.getTypeOfApplicant());
            applicant.setBirthdayDate(applicantFront.getDateOfBirthday());
            applicant.setIncome(applicantFront.getIncome());
            applicant.setSex(Gender.findByName(applicantFront.getGender()));
            applicant.setExperience(applicantFront.getExperience());
            applicant.setMaritalStatus(applicantFront.getMaritalStatus());
            applicant.setEducation(applicantFront.getEducation());
            applicant.setChildrenQuantity(applicantFront.getChildrenQuantity());
            applicant.setPersonalNumber(applicantFront.getPersonalNumber());
            applicant.setPhones(createSetPhones(applicantFront.getPhones(), applicant));
            applicant.setAddresses(createSetAddresses(applicantFront.getAddresses(), applicant));
            applicant.setApplication(application);
            applicants.add(applicant);
        }
        return applicants;
    }



}
