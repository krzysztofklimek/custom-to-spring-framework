package pl.insert;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.insert.configuration.Config;
import pl.insert.dao.UserDao;
import pl.insert.model.User;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main (String [] args){



        //ApplicationContext applicationContext = new ClassPathXmlApplicationContext(Config.class);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        UserDao userDao = (UserDao) applicationContext.getBean("userDao", UserDao.class);


        //--------------------dodawanie do bazy
        User user = new User();
        user.setName("spring");
        user.setSurname("spring");
        userDao.persist(user);


        //--------------------wyświetlanie całej listy
        List<User> users = new ArrayList<User>();
        users = (List<User>) userDao.getUsersList();
        System.out.println(users);

        //--------------------wyszukiwaqnie użytkownika
        User userById = userDao.getUserById((long) 5);
        System.out.println(userById);

        //--------------------usuwanie użytkownika
        User userToDelete = new User();
        userToDelete.setId(120);
        //userToDelete.setName("spring");
        //userToDelete.setSurname("Security");
        userDao.deleteUser(userToDelete);


    }


}
