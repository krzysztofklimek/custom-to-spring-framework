package pl.insert.dao;

import org.springframework.transaction.annotation.Transactional;
import pl.insert.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class UserDaoImpl implements UserDao {


    @PersistenceContext
    private EntityManager entityManager;


    public void persist(User user) {
        entityManager.persist(user);
    }


    public List<?> getUsersList() {
        List<?> users = entityManager.createQuery("SELECT user FROM User user").getResultList();
        return users;
    }



    public User getUserById(Long userId) {
        User user = entityManager.find(User.class, Long.valueOf(userId));
        //entityManager.flush();
        //entityManager.clear();
        //entityManager.close();
        return user;
    }

    public User getSecondUserById(Long userId){
        User user = entityManager.find(User.class, Long.valueOf(userId));
        return user;
    }


    public void deleteUser(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }



    public void updateUserSurname(long id, String surname){
        Query query = entityManager.createQuery("update User set surname = :surname where id = :id");
        query.setParameter("surname", surname);
        query.setParameter("id", id);
        query.executeUpdate();
    }





}
