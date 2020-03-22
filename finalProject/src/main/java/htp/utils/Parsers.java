package htp.utils;

import htp.entities.db_entities.Application;
import htp.entities.db_entities.Roles;
import htp.entities.db_entities.User;
import htp.entities.db_entities.Applicant;
import htp.entities.db_entities.Phone;
import htp.entities.db_entities.Address;
import htp.entities.front_entities.AddressFront;
import htp.entities.front_entities.ApplicationFront;
import htp.entities.front_entities.ApplicantFront;
import htp.entities.front_entities.PhoneFront;
import htp.entities.front_entities.UserF;

import java.sql.Timestamp;
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

    public static User createUser (UserF request){
        User user = new User();
        user.setLogin(request.getLogin());
        user.setPassword(request.getPassword());
        user.setCreated(new Timestamp(System.currentTimeMillis()));
        user.setChanged(user.getCreated());
        Set<Roles> roles = new HashSet<>();
        roles.add(new Roles("ROLE_USER", user));
        user.setRoles(roles);
        user.setIsdeleted(false);
        return user;
    }

    public static Application createApplication(ApplicationFront request){
        Application application = new Application();
        application.setUserId(1L);
        application.setCreationDate(new Timestamp(System.currentTimeMillis()));
        application.setLoanType(request.getLoanType());
        application.setProductCode(request.getProductCode());
        application.setLoanAmount(request.getLoanAmount());
        application.setCurrency(Dictionaries.setMyCurrency(request.getCurrency()));
        application.setApplicants(createSetApplicants(request.getApplicants(), application));
        return new Application();
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
            address.setAddress(addressFront.getAddress());
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
            applicant.setIncome(applicantFront.getIncome().longValue());
            applicant.setIncomeCurrency(Dictionaries.setMyCurrency(applicantFront.getIncomeCurrency()));
            applicant.setSex(Dictionaries.setGender(applicantFront.getGender()));
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
