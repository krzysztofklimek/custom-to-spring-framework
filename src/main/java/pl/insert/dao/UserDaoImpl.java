package pl.insert.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pl.insert.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
import java.util.List;

public class UserDaoImpl implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;


    public void persist(User user) {
        entityManager.persist(user);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void persistWithMandatory(User user){
        entityManager.persist(user);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void persistWithNested(User user){
        entityManager.persist(user);
    }

    @Transactional(propagation = Propagation.NEVER)
    public void persistWithNever(User user){
        entityManager.persist(user);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void persistWithNotSupported(User user){
        entityManager.persist(user);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void persistWithRequired(User user){
        entityManager.persist(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void persistWithRequiresNew(User user){
        entityManager.persist(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void persistWithSupports(User user){
        entityManager.persist(user);
    }


    @Transactional
    public List<?> getUsersList() {
        List<?> users = entityManager.createQuery("SELECT user FROM User user").getResultList();
        return users;
    }


    @Transactional
    public User getUserById(Long userId) {
        User user = entityManager.find(User.class, Long.valueOf(userId));
        return user;
    }


    @Transactional
    public void deleteUser(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }


}
