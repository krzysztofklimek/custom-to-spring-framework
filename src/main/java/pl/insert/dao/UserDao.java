package pl.insert.dao;

import pl.insert.model.User;

import java.util.List;

public interface UserDao {


    void persist(User user);

    List<?> getUsersList();
    User getUserById(Long userId);
    User getSecondUserById(Long userId);
    void deleteUser(User user);
    void updateUserSurname(long id, String surname);


}
