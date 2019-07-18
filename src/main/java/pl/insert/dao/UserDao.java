package pl.insert.dao;

import pl.insert.model.User;

import java.util.List;

public interface UserDao {


    void persist(User user);

    void persistWithMandatory(User user);
    void persistWithNested(User user);
    void persistWithNever(User user);
    void persistWithNotSupported(User user);
    void persistWithRequired(User user);
    void persistWithRequiresNew(User user);
    void persistWithSupports(User user);


    List<?> getUsersList();
    User getUserById(Long userId);
    void deleteUser(User user);


}
