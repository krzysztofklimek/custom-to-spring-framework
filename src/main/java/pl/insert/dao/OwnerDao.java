package pl.insert.dao;

import pl.insert.model.Owner;
import pl.insert.model.User;

public interface OwnerDao {


    void persist(Owner owner);
    Owner getOwnerById(Long userId);
    Owner findOwnerByEmail(String email);



}
