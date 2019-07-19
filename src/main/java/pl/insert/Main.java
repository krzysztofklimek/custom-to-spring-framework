//https://codete.com/blog/5-common-spring-transactional-pitfalls/

package pl.insert;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.insert.configuration.Config;
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
        user.setName("AspectJ");
        user.setSurname("surname");

        userService.updateUser(123, "asdasdasdasdasdasdasdasdasd");


        //userService.addUserWithMandatory(user);

        //userService.addUserWithNested(user);

        //userService.addUserWithNever(user);

        //userService.addUserWithRequired(user);

        //userService.addUserWithRequired(user);

        //userService.addUserWithRequiresNew(user);

        //userService.addUserWithSupports(user);
    }


}
