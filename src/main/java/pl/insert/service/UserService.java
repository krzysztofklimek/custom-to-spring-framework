package pl.insert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.*;
import pl.insert.dao.UserDao;
import pl.insert.model.User;


import java.util.List;

public class UserService {

    @Autowired
    UserDao userDao;


    @Transactional
    public void addUser(User user){
        userDao.persist(user);
    }


    @Transactional
    public void addUserWithMandatory(User user){
        nestedAddUserWithMandatory(user);
        //userDao.persistWithMandatory(user);
        throw new RuntimeException("wycofuje zmiany");
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void nestedAddUserWithMandatory(User user){
        userDao.persist(user);
    }

    @Transactional
    public void addUserWithNested(User user){
        userDao.persistWithNested(user);
        throw new RuntimeException("");
    }

    @Transactional
    public void addUserWithNever(User user){
        userDao.persistWithNever(user);
        throw new RuntimeException("");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addUserWithNotSupported(User user){
        userDao.persistWithNotSupported(user);
        throw new RuntimeException("");
    }

    @Transactional
    public void addUserWithRequired(User user){
        userDao.persistWithRequired(user);
        throw new RuntimeException("Wycofujemy zmiany");
    }



    tutaj
    @Transactional
    public void addUserWithRequiresNew(User user){
        nestedAddUserWithRequiresNew(user);
        //userDao.persistWithRequiresNew(user);
        throw new RuntimeException("Użytkownik doda się pomimo wyjątku");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void nestedAddUserWithRequiresNew(User user){
        this.userDao.persist(user);
    }

    @Transactional
    public void addUserWithSupports(User user){
        userDao.persistWithSupports(user);
        throw new RuntimeException("");
    }


    @Transactional
    public void deleteUser(long userId){
        User user = new User();
        user.setId(userId);
        userDao.deleteUser(user);
    }

    @Transactional
    public List<?> getUserList(){
        List <?> users = userDao.getUsersList();
        return users;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<?> getUserListWithSerializable(){
        List <?> users = userDao.getUsersList();
        return users;
    }

}
