package TestGit;

import htp.config.AppConfig;
import htp.dao.DAOinterfaces.ApplicationRepository;
import htp.dao.DAOinterfaces.UserRepository;
import htp.entities.Application;
import htp.entities.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Timestamp;
import java.util.List;


public class TestGit {
    public static void main(String[] args){
        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//        UserRepository userDao = (UserRepository) ctx.getBean("userRepSpringImp");
//        System.out.println(userDao.findById(4L));
//        System.out.println(userDao.save(new User(7L, "Batman1", "Robin1", new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()), "user", false)));
//        System.out.println(userDao.save(new User(7L, "Batman2", "Robin2", new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()), "user", false)));
//        userDao.fakeDelete(1L);
//        List<User> userList;
//        userDao.delete(3L);
//        userList = userDao.findAll();
//        for (User user: userList){
//            System.out.println(user);
//        }

        ApplicationRepository apllicationDAO = (ApplicationRepository) ctx.getBean("applicationRepSpringImpl");
        System.out.println(apllicationDAO.findOne("1-1_2020"));
        for (Application application: apllicationDAO.findAll()){
            System.out.println(application);
        }
    }
}
