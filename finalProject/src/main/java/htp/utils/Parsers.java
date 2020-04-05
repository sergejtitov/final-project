package htp.utils;

import htp.controller.request.*;
import htp.domain.model.*;
import htp.domain.dictionaries.Gender;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            phone.setPhoneType(phoneFront.getPhoneType());
            phone.setPhoneNumber((phoneFront.getPhoneNumber()));
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

    public static CreditInfo createCreditInfo (Application application, Product product){
        CreditInfo creditInfo = new CreditInfo();
        creditInfo.setLoanAmount(application.getFinalAmount());
        creditInfo.setInterestRate(product.getInterestRate());
        creditInfo.setBalanceAmount(application.getFinalAmount());
        creditInfo.setBalanceTerm(product.getLoanTerm());
        creditInfo.setPayment(application.getPayment());
        creditInfo.setApplicationId(application.getApplicationId());
        creditInfo.setPersonalNumber(Functions.findMainApplicantPersonalNumber(application.getApplicants()));
        return creditInfo;
    }

}
