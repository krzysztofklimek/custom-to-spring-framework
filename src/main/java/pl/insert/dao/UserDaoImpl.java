package pl.insert.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.insert.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class UserDaoImpl implements UserDao {




    @PersistenceContext
    private EntityManager entityManager;



    @Transactional
    public void persist(User user) {
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
