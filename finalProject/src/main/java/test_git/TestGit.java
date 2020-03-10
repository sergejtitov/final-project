package test_git;


import htp.dao.UserRepository;
import htp.entities.db_entities.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;

public class TestGit {
/*    private static void testUserImpl(){
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        UserRepository userDao = (UserRepository) ctx.getBean("userRepSpringImp");
        CallsCounter callsCounter = (CallsCounter) ctx.getBean("callsCounter");
        System.out.println(userDao.findById(4L));
        System.out.println(userDao.save(new User(7L, "Batman3", "Robin3", new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()), "user", false)));
        System.out.println(userDao.save(new User(7L, "Batman4", "Robin4", new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()), "user", false)));
        userDao.fakeDelete(4L);
        List<User> userList;
        userDao.delete(3L);
        userList = userDao.findAll(3,3);
        for (User user: userList){
            System.out.println(user);
        }
        System.out.println(callsCounter.getCounter());
    }
*/

   /* private static void testApplicationImpl(){
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ApplicationRepSpringImpl applicationDAO = (ApplicationRepSpringImpl) ctx.getBean("applicationRepSpringImpl");
        CallsCounter callsCounter = (CallsCounter) ctx.getBean("callsCounter");
        //System.out.println(applicationDAO.save(new Application(1L, 6L, new Timestamp(System.currentTimeMillis()),new LoanType(4), new ProductCode(4003L), 1000D, MyCurrency.BYN, 0D, Decision.PROCEED, Status.PROCEED, 0D)));
        System.out.println(applicationDAO.findById(8L));
        applicationDAO.update(new Application(6L, 9L, new Timestamp(System.currentTimeMillis()),new LoanType(3), new ProductCode(3001L), 1000D, MyCurrency.BYN, 0D, Decision.ACCEPT, Status.CUSTOMER_FAILURE, 0D));
        List<Application> applicationList = applicationDAO.findApplicationByUserId(6L);
        for (Application application: applicationList){
            System.out.println(application);
        }
        //applicationDAO.delete(7L);
        List<Application> applications = applicationDAO.findAll(2,2);
        for (Application application: applications){
            System.out.println(application);
        }
        System.out.println(callsCounter.getCounter());
    }*/
/*
    public static void testApplicantImpl(){
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ApplicantRepSpringImpl applicantDAO = (ApplicantRepSpringImpl) ctx.getBean("applicantRepSpringImpl");
        CallsCounter callsCounter = (CallsCounter) ctx.getBean("callsCounter");
        System.out.println(applicantDAO.update(new Applicant(4L, "Bruce", "Wayne", "", 1, new Timestamp(System.currentTimeMillis()), 10000, "BYN", "M", 5, 4, 3, 2,"2342524534A111PB0", 2 )));
        System.out.println(applicantDAO.save(new Applicant(2L, "Christian", "Bale", "", 2, new Timestamp(System.currentTimeMillis()-100000), 100, "BYN", "M", 5, 4, 3, 0,"2352524554A11PB0", 2 )));
        System.out.println(applicantDAO.findById(4L));
        List<Applicant> applicants = applicantDAO.findAll(10,0);
        for (Applicant applicant : applicants){
            System.out.println(applicant);
        }
        for (Applicant applicant : applicantDAO.findByApplication(3L)){
            System.out.println(applicant);
        }
        applicantDAO.delete(8L);
        System.out.println(callsCounter.getCounter());
    }*/
public static void main(String[] args){

    //testAddress();
    //testUserImpl();
    //testApplicationImpl();
    //testApplicantImpl();

}

    /*public static void testAddress(){
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        CallsCounter callsCounter = (CallsCounter) ctx.getBean("callsCounter");
        AddressRepSpringImpl addressDAO = (AddressRepSpringImpl) ctx.getBean("addressRepSpringImpl");
        System.out.println(addressDAO.save(new Address(4L, 5L, "Bakery street ", "work")));
        System.out.println(callsCounter.getCounter());
    }*/
}
