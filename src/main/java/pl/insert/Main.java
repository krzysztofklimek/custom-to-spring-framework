package pl.insert;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.insert.configuration.Config;
import pl.insert.dao.UserDao;
import pl.insert.model.User;
import pl.insert.service.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class Main {

//    @Transactional
//    public static void addUser(UserDao userDao, User user){
//        userDao.persist(user);
//        throw new RuntimeException("Wycofujemy zmiany");
//    }



    public static void main (String [] args) throws InterruptedException {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);

        //UserDao userDao = (UserDao) applicationContext.getBean("userDao", UserDao.class);
        final UserService userService = applicationContext.getBean("userService", UserService.class);


        User user = new User();
        user.setName("name");
        user.setSurname("surname");

        //userService.addUserWithMandatory(user);

        //userService.addUserWithNested(user);

        //userService.addUserWithNever(user);

        //userService.addUserWithRequired(user);

        //userService.addUserWithRequired(user);

        userService.addUserWithRequiresNew(user);

        //userService.addUserWithSupports(user);
    }


}
