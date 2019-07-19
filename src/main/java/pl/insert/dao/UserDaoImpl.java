package pl.insert.dao;

import org.springframework.transaction.annotation.Transactional;
import pl.insert.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        return user;
    }



    public void deleteUser(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }



    public void updateUserSurname(long id, String surname){
        User user = entityManager.find(User.class, id);

        System.out.println(user);
//        entityManager.createQuery("update User set surname = :surname where id = :id")
//                .setParameter("surname", surname)
//                .setParameter("id", id)
//                .executeUpdate();

        user.setSurname(surname);
//        entityManager.merge(user);


    }



}
