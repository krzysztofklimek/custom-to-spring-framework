package pl.insert.dao;

import pl.insert.model.User;

import java.util.List;

public interface UserDao {


    void persist(User user);
    List<?> getUsersList();
    User getUserById(Long userId);
    void deleteUser(User user);


}
