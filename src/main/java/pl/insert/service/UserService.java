package pl.insert.service;

import org.hibernate.annotations.OptimisticLock;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import pl.insert.dao.UserDao;
import pl.insert.model.User;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService _this;


    @Transactional
    public void addUser(User user){
        userDao.persist(user);
    }

    @Transactional
    public User findUserById(Long id){
        User user = userDao.getUserById(id);
        return user;
    }


    @Transactional
    public void addUserWithMandatory(User user){
        _this.selfInvocationAddUserWithMandatory(user);
        throw new RuntimeException("wycofuje zmiany");
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void selfInvocationAddUserWithMandatory(User user){
        userDao.persist(user);
    }

    @Transactional
    public void addUserWithNested(User user){
        _this.selfInvocationAddUserWithNested(user);
        throw new RuntimeException("");
    }

    @Transactional
    public void selfInvocationAddUserWithNested(User user){
        userDao.persist(user);
    }

    @Transactional
    public void addUserWithNever(User user){
        _this.selfInvocationAddUserWithNever(user);
        throw new RuntimeException("");
    }

    @Transactional
    public void selfInvocationAddUserWithNever(User user){
        userDao.persist(user);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addUserWithNotSupported(User user){
        _this.selfInvocationAddUserWithNotSupported(user);
        throw new RuntimeException("");
    }

    @Transactional
    public void selfInvocationAddUserWithNotSupported(User user){
        userDao.persist(user);
    }

    @Transactional
    public void addUserWithRequired(User user){
        _this.selfInvocationAddUserWithRequired(user);
        throw new RuntimeException("Wycofujemy zmiany");
    }

    @Transactional
    public void selfInvocationAddUserWithRequired(User user){
        userDao.persist(user);
    }


    @Transactional
    public void addUserWithRequiresNew(User user){
        _this.nestedAddUserWithRequiresNew(user);
        throw new RuntimeException("Użytkownik doda się pomimo wyjątku");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void nestedAddUserWithRequiresNew(User user){
        userDao.persist(user);
    }

    @Transactional
    public void addUserWithSupports(User user){
        _this.selfInvocationAddUserWithSupports(user);
        throw new RuntimeException("");
    }

    @Transactional
    public void selfInvocationAddUserWithSupports(User user){
        userDao.persist(user);
    }


    @Transactional
    public void deleteUser(long userId){
        User user = new User();
        user.setId(userId);
        userDao.deleteUser(user);
    }

    //@Transactional
    public List<?> getUserList(){
        List <?> users = userDao.getUsersList();
        return users;
    }

    @Transactional
    public void updateUserSurname(long id, String surname){
        userDao.updateUserSurname(id, surname);
    }

    @Transactional
    public int readUserTwice() throws InterruptedException {

        int firstSize, secondSize;

        firstSize = userDao.getUsersList().size();
        System.out.println(firstSize);
        Thread.sleep(3000);
        secondSize = userDao.getUsersList().size();
        System.out.println(secondSize);

        return secondSize - firstSize;
    }

    @Transactional
    public int addUserTwice() throws InterruptedException {


        User user = new User();
        user.setName("dirtyReads");
        user.setSurname("dirtyReads");

        userDao.persist(user);
        Thread.sleep(3000);


        User user2 = new User();
        user.setName("dirtyReads");
        user.setSurname("dirtyReads");

        userDao.persist(user2);

        return userDao.getUsersList().size();
    }

    @Transactional
    public boolean findUserValueTwice(long id) throws InterruptedException {

        User userOne = userDao.getUserById(id);
        String surnameOne = userOne.getSurname();

        Thread.sleep(3000);


//        String surnameTwo = null;
//
//        List<User> users = new ArrayList<>();
//        users = (List<User>) userDao.getUsersList();
//        for(User u :users){
//            if(u.getId()==id)
//                surnameTwo = u.getSurname();
//        }


        User userTwo = userDao.getSecondUserById(id);
        String surnameTwo = userTwo.getSurname();

        return surnameOne.equals(surnameTwo);
    }

}
